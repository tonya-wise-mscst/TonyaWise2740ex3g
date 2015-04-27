package TonyaWise2740ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper {
private String fileName;
private PrintWriter outputFile;
private Scanner inputFile;
private File file;


public PayrollObjMapper(String fileName) {
	super();
	this.fileName = fileName;
}

public boolean openInputFile() {
	boolean fileOpened = false;

	try {
		File file = new File(this.fileName);
		fileOpened = !file.exists();
		
		if (fileOpened) {
	this.inputFile = new Scanner(file);
		}
	}
	catch (IOException e) {}
	
	return fileOpened;
}

public boolean openOutputFile() {
	boolean fileOpened = false;
	try {
		this.outputFile = new PrintWriter(fileName);
		fileOpened = true;
	}
	catch (IOException e) {}
	
	return fileOpened;
}

public void closeInput() {
	if (this.inputFile != null) this.inputFile.close();
}

public void closeOutput() {
	if (this.outputFile != null) this.outputFile.close();
}

public Payroll getNextPayroll() {
	Payroll p = null;
	
	String textLine = inputFile.nextLine();
	int id = Integer.parseInt(textLine);
	String name = inputFile.nextLine();
	textLine = inputFile.nextLine();
	double payRate = Double.parseDouble(textLine);
	textLine = inputFile.nextLine();
	double hours = Double.parseDouble(textLine);
	p = new Payroll(id, name, payRate, hours);
			return p;
}

public void writePayroll(Payroll payroll) {
	if (this.outputFile != null && payroll != null) {
		outputFile.println(payroll.getId());
		outputFile.println(payroll.getName());
		outputFile.println(payroll.getPayRate());
		outputFile.println(payroll.getHours());
	}
}

public DefaultListModel getAllPayroll() {
	DefaultListModel payrollCollection = new DefaultListModel();
	
	if (this.openInputFile()) {
		while (this.inputFile.hasNext()) {
			Payroll p = this.getNextPayroll();
			if (p != null)
			payrollCollection.addElement(p);
		}
	}
	
	this.closeInput();
	return payrollCollection;
}

public void writeAllPayroll(DefaultListModel payrollCollection) {
	if (this.openOutputFile()) {
		payrollCollection.getSize();
		Payroll p = (Payroll) payrollCollection.get(0);
		this.writePayroll(p);
	}
	this.closeOutput();
}
}