package com.frame.learn.socket;

import java.io.*;
import java.net.Socket;


public class Processor extends Thread {
    private Socket socket;
    private InputStream in;
    private PrintStream out;

    private final  static String WEB_ROOT="E:\\myProjects\\webServer\\httpdocs";
    public Processor(Socket socket) {
        this.socket = socket;
        try {
            in = socket.getInputStream();
            out=new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
          String filename=parse(in);
          sendFile(filename);
    }

    public String parse(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String fileName = null;
        try {
            String httpMessage = br.readLine();
            String[] content = httpMessage.split(" ");
            if (content.length != 3) {
                sendErrorMessage(400, "Client query error");
                return null;
            }
            System.out.println("code:" + content[0] + ", filename" + content[1] + ", http version" + content[2]);
            fileName = content[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    private void sendErrorMessage(int errorCode, String errorMessage) {
        out.println("HTTP/1.0 "+errorCode+" "+errorMessage);
        out.println("content-type: text/html");
        out.println();
        out.println("<html>");
        out.println("<title>Error Message");
        out.println("</title>");
        out.println("<body>");
        out.println("<h1>ErrorCode:"+errorCode+",ErrorMeesage:"+errorMessage);
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String fileName) {
       File file=new File(Processor.WEB_ROOT+fileName);
        if(!file.exists()||fileName.equals("/")){
            sendErrorMessage(404,"File Not Found");
            return;
        }
        try {
            InputStream in=new FileInputStream(file);
            byte [] content=new byte[(int)file.length()];
            in.read(content);
            out.println("HTTP/1.0 200 queryfile");
            out.println("content-length:"+content.length);
            out.println();
            out.write(content);

            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
