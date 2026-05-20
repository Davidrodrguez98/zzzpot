package com.dacar.zzzpot.models;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String title;
	private String description;
	private int maxGuests;
	private double pricePerNight;
}
