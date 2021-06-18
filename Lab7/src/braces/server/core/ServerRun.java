package braces.server.core;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

import braces.Exchanger.ClientExchanger;

public class ServerRun {
	private static Selector selector = null;
	private static int valueOfByteBuffer = 65536;
	CommandManager commandManager;
	public  ServerRun(CommandManager commandManager) {
		this.commandManager = commandManager;
	}
	public void start() throws ClassNotFoundException, InterruptedException, IOException {
	
			selector = Selector.open();
			System.out.println("Waiting for client...");
			ServerSocketChannel socket = ServerSocketChannel.open();
			ServerSocket serverSocket = socket.socket();
			serverSocket.bind(new InetSocketAddress("localhost", 6963));
			socket.configureBlocking(false);
			int ops = socket.validOps();
			SelectionKey selectKy = socket.register(selector, ops, null);
			ClientExchanger exchangeClass = new ClientExchanger();

            TreeMap<String, Handler> handle = new TreeMap<>();
			while  (true){
				int noOfKeys = selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> i = selectedKeys.iterator();
				while (i.hasNext()) {
					SelectionKey key = i.next();
					  i.remove();
					  
		                if (!key.isValid()) {
		                    continue;
		                }
		 
					if (key.isAcceptable()) {
						SocketChannel client = socket.accept();
						client.configureBlocking(false);
						// The new connection is added to a selector
						System.out.println("Connected");
						System.out.println("The new connection is accepted from the client: " + client);
						handle.put(client.toString(), new Handler(commandManager));
						client.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
					/*	SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(65536);
						int numRead = -1;
						numRead = client.read(buffer);
				        if (numRead == -1) {
				            Socket socket1 = client.socket();
				            SocketAddress remoteAddr = socket1.getRemoteSocketAddress();
				            System.out.println("Connection closed by client: " + remoteAddr);
				            client.close();
				            key.cancel();
				            return;
				        }
						client.read(buffer);
						String output = new String(buffer.array()).trim();
						exchangeClass = deserialize(buffer.array()); 
			           
						System.out.println(exchangeClass);*/
						SocketChannel client = (SocketChannel) key.channel();  
                        ByteBuffer buffer = ByteBuffer.allocate(65536); 
                        int numRead = -1;
                        try {

    						numRead = client.read(buffer);
						} catch (SocketException e) {
							
							continue;
						}
						if (numRead == -1) {
				            Socket socket1 = client.socket();
				            SocketAddress remoteAddr = socket1.getRemoteSocketAddress();
				            System.out.println("Connection closed by client: " + remoteAddr);
				            client.close();
				            key.cancel();
				            continue;
				        }
                        String output = new String(buffer.array()).trim();  
                        exchangeClass = deserialize(buffer.array());
                        exchangeClass= handle.get(client.toString()).startCommand(exchangeClass);
                        System.out.println(exchangeClass);
						client.register(selector, SelectionKey.OP_WRITE);
					} else if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(65536);
						clear(buffer);
						buffer.put(serialize(exchangeClass));
						flip(buffer);

						while (buffer.hasRemaining()) {
							client.write(buffer);
						}
						client.register(selector, SelectionKey.OP_READ);
					}
				}
			}
		} 
	

	
	/*
	 * public void start() throws ClassNotFoundException, InterruptedException,
	 * ExecutionException { try { selector = Selector.open(); ServerSocketChannel
	 * socket = ServerSocketChannel.open(); ServerSocket serverSocket =
	 * socket.socket(); System.out.println("Server is started...");
	 * serverSocket.bind(new InetSocketAddress("localhost", 6963));
	 * socket.configureBlocking(false); int ops = socket.validOps();
	 * socket.register(selector, ops, null); while (true) { selector.select();
	 * Set<SelectionKey> selectedKeys = selector.selectedKeys();
	 * Iterator<SelectionKey> i = selectedKeys.iterator(); ClientExchanger
	 * exchangeClass = new ClientExchanger(); while (i.hasNext()) { SelectionKey key
	 * = i.next(); if (key.isAcceptable()) { handleAccept(socket, key); } else if
	 * (key.isReadable()) { SocketChannel client = (SocketChannel) key.channel();
	 * ByteBuffer buffer = ByteBuffer.allocate(65536); client.read(buffer); String
	 * output = new String(buffer.array()).trim(); exchangeClass =
	 * deserialize(buffer.array()); exchangeClass=
	 * handler.startCommand(exchangeClass); System.out.println(exchangeClass);
	 * client.register(selector, SelectionKey.OP_WRITE); } else if
	 * (key.isWritable()) { SocketChannel client = (SocketChannel) key.channel();
	 * ByteBuffer buffer = ByteBuffer.allocate(65536); clear(buffer);
	 * buffer.put(serialize(exchangeClass)); flip(buffer);
	 * 
	 * while(buffer.hasRemaining()) { System.out.println(buffer);
	 * client.write(buffer); } client.register(selector, SelectionKey.OP_READ); }
	 * i.remove(); } } } catch (BindException e) {
	 * System.out.println("Can't use port"); System.exit(-1); } catch (IOException
	 * e) { System.out.println("Can't use port"); System.exit(-1); } }
	 */
	private static void handleAccept(ServerSocketChannel mySocket, SelectionKey key) throws IOException {
		System.out.println("Conection Accepted");
		SocketChannel client = mySocket.accept();
		client.configureBlocking(false);
		client.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Lmfao it useless
	 * 
	 * @param key
	 * @param exchangeClass
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void handleRead(SelectionKey key, ClientExchanger exchangeClass)
			throws ExecutionException, InterruptedException, IOException, ClassNotFoundException {

	}

	/**
	 * Idk why i have to use clear indirectly
	 * 
	 * @param buffer
	 */
	public static void clear(Buffer buffer) {
		buffer.clear();
	}

	/**
	 * Idk why i have to use clear indirectly
	 * 
	 * @param buffer
	 */
	public static void flip(Buffer buffer) {
		buffer.flip();
	}

	private void handleWrite(SelectionKey key) {
		SocketChannel client = (SocketChannel) key.channel();
		try {
			ByteBuffer buffer = ByteBuffer.allocate(65536);
			clear(buffer);
			ClientExchanger exchangeClass = new ClientExchanger();
			buffer.put(serialize(exchangeClass));
			flip(buffer);

			while (buffer.hasRemaining()) {
				client.write(buffer);
			}
			client.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			try {
				client.close();
			} catch (IOException e1) {
				System.out.println(" Server Handle Read.");
			}
			System.out.println("Client disconnected");
		}
	}

	public byte[] serialize(Object obj) throws IOException {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(obj);
			}
			return b.toByteArray();
		}
	}

	public ClientExchanger deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
			try (ObjectInputStream o = new ObjectInputStream(b)) {
				return (ClientExchanger) o.readObject();
			}
		}
	}
}
