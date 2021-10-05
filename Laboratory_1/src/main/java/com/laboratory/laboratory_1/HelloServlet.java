package com.laboratory.laboratory_1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private String confirmation;

    public void init() {
        message = "Hello!";
        confirmation = "I received the data!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logInfo(request);

        String key = request.getParameter("key");
        int value = Integer.parseInt(request.getParameter("value"));
        boolean mock = request.getParameter("mock") != null;
        boolean sync = request.getParameter("sync") != null;

        if(mock) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h1>" + confirmation + "</h1></body></html>");
        }
        else {
            if (sync)
                writeSync(value, key);
            else writeAsync(value, key);

            try(Scanner repoReader = new Scanner(new File("C:/Users/sdragoi/Desktop/repository.txt"))) {
                List<String> fileLines = new ArrayList<>();
                while (repoReader.hasNextLine()) {
                    String line = repoReader.nextLine();
                    fileLines.add(line);
                }

                fileLines.sort(Comparator.naturalOrder());

                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body><p>");
                for (String s : fileLines) {
                    out.println(s + "<br/>");
                }
                out.println("</p></body></html>");

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void writeSync(int value, String key) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/sdragoi/Desktop/repository.txt"))) {

            synchronized (writer) {
                for (int i = 0; i < value; i++) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    writer.write(String.format("%s (%s) ", key, timestamp));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeAsync(int value, String key) {
        Path path = Paths.get("C:/Users/sdragoi/Desktop/repository.txt");

            try (AsynchronousFileChannel asyncChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
                for(int i = 0 ; i < value; i++) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String toWrite = String.format("%s (%s) ", key, timestamp);
                    asyncChannel.write(ByteBuffer.wrap(toWrite.getBytes()), (long) i * toWrite.length() + 1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void logInfo(HttpServletRequest request) {
        log("HTTP method: " + request.getMethod());
        log("IP address: " + request.getRemoteAddr());
        log("User-agent: " + request.getHeader("USER-AGENT"));
        log("Language: " + request.getHeader("Accept-Language"));

        Enumeration<String> en = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        while (en.hasMoreElements())
            sb.append(en.nextElement()).append(", ");
        log("Parameters: " + sb.toString());
    }

    public void destroy() {
    }
}