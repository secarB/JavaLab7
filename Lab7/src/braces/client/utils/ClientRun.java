package braces.client.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

import braces.Exchanger.ClientExchanger;
import braces.server.fields.SpaceMarine;

public class ClientRun  {
	private static int valueOfByteBuffer = 65536;
	private static SocketChannel client;
	private static ByteBuffer data;

	public static void run() {
		boolean connect = false;
		while (!connect) {
			try {
				System.out.println("Connecting...");
				client = SocketChannel.open(new InetSocketAddress("localhost", 6963));
				data = ByteBuffer.allocate(valueOfByteBuffer);
				System.out.println("Connected");
				TimeUnit.MILLISECONDS.sleep(100);
				connect = true;
			} catch (IOException | InterruptedException ex) {
				try {
					System.err.println("No connection. Type 1 to try again, 0 to exit.");
					String re;
					Scanner scanner = new Scanner(System.in);
					while (!(re = scanner.nextLine().trim()).equals("1")) {
						switch (re) {
						case "":
							System.out.println();
							continue;
						case "0":
							System.exit(0);
							break;
						default:
							System.out.println("Type 1 or 0.");
						}
					}
					continue;
				} catch (NoSuchElementException e) {
					System.exit(0);
				}
			} catch (NoSuchElementException ex) {
				System.exit(0);
			}
		}
	}

	public static void clear(Buffer buffer) {
		buffer.clear();
	}

	public static void flip(Buffer buffer) {
		buffer.flip();
	}

	public static void writeObject(ClientExchanger testClass) {
		/*
		 * try { data = ByteBuffer.allocate(valueOfByteBuffer);
		 * data.put(serialize(testClass)); data.flip(); client.write(data);
		 * readObject(); } catch (IOException e) { System.out.println("1243"); start();
		 * writeObject(testClass); }
		 */
		try {
			ByteBuffer buffers = ByteBuffer.wrap(serialize(testClass));
			client.write(buffers);
			((Buffer) buffers).clear();
		} catch (Exception e) {
			System.out.println("No connection");

		}

	}

	public static void readObject() {
		try {
			ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
			client.read(byteBuffer);
			ClientExchanger testClass = deserialize(byteBuffer.array());
			System.out.println(testClass.getAnswer());
		} catch (Exception e) {
			System.out.println("No connection");
		}

	}

	public static byte[] serialize(Object obj) throws IOException {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(obj);
			}
			return b.toByteArray();
		}
	}

	public static ClientExchanger deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
			try (ObjectInputStream o = new ObjectInputStream(b)) {
				return (ClientExchanger) o.readObject();
			}
		}
	}

	public static void readyToExchange(String name, String argument, SpaceMarine spaceMarine)
			throws IOException, InterruptedException {
		ClientExchanger exchangeClass = new ClientExchanger();
		exchangeClass.setName(name);
		exchangeClass.setArgument(argument);
		exchangeClass.setSpaceMarine(spaceMarine);
		writeObject(exchangeClass);
		readObject();

	}
}
