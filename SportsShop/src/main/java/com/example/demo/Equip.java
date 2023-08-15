package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equip {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		private String name;
		private String game;
		private String qty;
		private String company;
		private String price;
		private String warrenty;
		@Column(length=1000000)
		private byte[] image;
		public Equip(int id, String name, String game, String qty, String company, String price, String warrenty,
				byte[] image) {
			super();
			this.id = id;
			this.name = name;
			this.game = game;
			this.qty = qty;
			this.company = company;
			this.price = price;
			this.warrenty = warrenty;
			this.image = image;
		}
		public Equip() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGame() {
			return game;
		}
		public void setGame(String game) {
			this.game = game;
		}
		public String getQty() {
			return qty;
		}
		public void setQty(String qty) {
			this.qty = qty;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getWarrenty() {
			return warrenty;
		}
		public void setWarrenty(String warrenty) {
			this.warrenty = warrenty;
		}
		public byte[] getImage() {
			return image;
		}
		public void setImage(byte[] image) {
			this.image = image;
		}
		
}
