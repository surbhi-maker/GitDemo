package com.element.fleet.ordering.verification;

public class SummaryTab {
private PDF pdfObject;

	public PDF getpdfObject() {
		if (pdfObject == null) {
			this.pdfObject = new PDF();
		}
		return this.pdfObject;
	}

public class PDF {
	private String pdfText;

	public String getpdfText() {
		return pdfText;
	}

	public void setpdfText(String pdfText) {
		this.pdfText = pdfText;
	}

	public String toString() {
		return "\t" + "\t" + "pdf text: " + pdfText + "\n";
	}
}}
