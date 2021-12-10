package tn.esprit.spring.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class APIResponse <T> {
	 int recordCount;
	    T response;
}