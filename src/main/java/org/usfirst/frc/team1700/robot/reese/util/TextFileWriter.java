// This is 254's 

package org.usfirst.frc.team1700.robot.reese.util;

import java.io.*;

import edu.wpi.first.wpilibj.DriverStation;
import java.io.IOException;
import java.nio.file.Files;

public class TextFileWriter {

	// this is probably wrong

    private BufferedWriter writer;

	public TextFileWriter(String uri) {
		try {
			File file = new File(uri);
			if (!file.exists()) {
				System.out.println("TextFileReader could not find specified file!");
				//DriverStation.reportWarning("TextFileReader could not find specified file!", false);
				return;
			}

			writer = Files.newBufferedWriter(file.toPath());

		} catch (IOException e) {
			e.printStackTrace();
			DriverStation.reportWarning("TextFileReader Could not open file connection!", false);
			closeFile();
		}
	}

	public void closeFile() {
		try {
			writer.close();
		} catch (IOException e) {
			DriverStation.reportWarning("TextFileReader Could not close file", false);
		}
	}

	public void writeLine(String content) {
		try {
            writer.write(content);
            writer.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
			closeFile();
		}
	}
}