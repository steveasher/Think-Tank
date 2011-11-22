package org.steve.fluent.constraints.zebra;

import java.util.EnumSet;

import org.steve.fluent.constraints.UnknownState;
import org.steve.fluent.constraints.zebra.ListCreator.GrabStrategy;

public class House {
	public final UnknownState<Color> color = new UnknownState<Color>(EnumSet.allOf(Color.class));
	public final UnknownState<Beverage> beverage = new UnknownState<Beverage>(EnumSet.allOf(Beverage.class));
	public final UnknownState<Cigarettes> cigarettes = new UnknownState<Cigarettes>(EnumSet.allOf(Cigarettes.class));
	public final UnknownState<Nationality> nationality = new UnknownState<Nationality>(EnumSet.allOf(Nationality.class));
	public final UnknownState<Pet> pet = new UnknownState<Pet>(EnumSet.allOf(Pet.class));
	
	public final static GrabStrategy<House, UnknownState<Color>> colorGrabber = new GrabStrategy<House,UnknownState<Color>>(){
		public UnknownState<Color> grab(House input) {
			return input.color;
		}
	};
	
	public final static GrabStrategy<House, UnknownState<Beverage>> beverageGrabber = new GrabStrategy<House,UnknownState<Beverage>>(){
		public UnknownState<Beverage> grab(House input) {
			return input.beverage;
		}
	};
	
	public final static GrabStrategy<House, UnknownState<Cigarettes>> cigarettesGrabber = new GrabStrategy<House,UnknownState<Cigarettes>>(){
		public UnknownState<Cigarettes> grab(House input) {
			return input.cigarettes;
		}
	};
	
	public final static GrabStrategy<House, UnknownState<Nationality>> nationalityGrabber = new GrabStrategy<House,UnknownState<Nationality>>(){
		public UnknownState<Nationality> grab(House input) {
			return input.nationality;
		}
	};
	
	public final static GrabStrategy<House, UnknownState<Pet>> petGrabber = new GrabStrategy<House,UnknownState<Pet>>(){
		public UnknownState<Pet> grab(House input) {
			return input.pet;
		}
	};
	
	@Override
	public String toString() {
		return "color: " + this.color + " beverage: " + this.beverage + 
				" nationality: " + this.nationality + " cigarettes: " + this.cigarettes + " pet: " + this.pet;
	}
}
