// This is 254's 

package org.usfirst.frc.team1700.robot.reese.util;

import java.io.*;

import edu.wpi.first.wpilibj.DriverStation;
import java.io.IOException;
import java.nio.file.Files;

public class TextFileReader {

	private BufferedReader reader_ = null;

	public TextFileReader(String uri) {
		try {
			// Open the new file
			File file = new File(uri);
			if (!file.exists()) {
				DriverStation.reportWarning("TextFileReader could not find specified file!", false);
				return;
			}

			// Make an I/O adapter sandwich to actually get some text out
			reader_ = Files.newBufferedReader(file.toPath());

		} catch (IOException e) {
			e.printStackTrace();
			DriverStation.reportWarning("TextFileReader Could not open file connection!", false);
			closeFile();
		}
	}

	private void closeFile() {
		try {
			// If we have a file open, close it
			reader_.close();
		} catch (IOException e) {
			DriverStation.reportWarning("TextFileReader Could not close file", false);
		}
	}

	// Returns null at end of file
	public String readLine() {
		String line = null;
		try {
			line = reader_.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			closeFile();
		}
		return line;
	}

	public String readWholeFile() {
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = readLine()) != null) {
			buffer.append(line);
			buffer.append("\n");
		}
		return buffer.toString();
	}

}