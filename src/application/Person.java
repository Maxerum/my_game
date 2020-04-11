package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {


	private SimpleStringProperty name;
	private SimpleStringProperty record;

	Person(){
		
	}
	
	Person(String name, String age) {
		this.name = new SimpleStringProperty(name);
		this.record = new SimpleStringProperty(age);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String value) {
		name.set(value);
	}

	public String getAge() {
		return record.get();
	}

	public void setAge(String value) {
		record.set(value);
	}


}
