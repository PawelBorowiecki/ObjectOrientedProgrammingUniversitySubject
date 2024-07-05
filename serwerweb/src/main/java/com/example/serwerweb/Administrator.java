package com.example.serwerweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.Socket;

public class Administrator extends Client{

    public Administrator(Socket socket) {
        super(socket);
    }

    public void video() throws FileNotFoundException {
        File mp4File = new File("/home/student/Kolokwium2/serwerweb/history.mp4");
        PrintWriter writer = new PrintWriter(mp4File);
        writer.close();
    }
}
