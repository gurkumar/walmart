package main.java.input;

public class Address {
	
	
		private String address1;
		private String address2;
		private String city;
		private String state;
		private String zip;
		private String country;
		
		public Address (String address1, String address2, String city, String state, String zip,String country) {
			this.address1 = address1;
			this.address2 = address2 ;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.country = country;
		}
		
		public String getAddress1 () {
			return this.address1;
		}
		
		public String getAddress2 () {
			return this.address2;
		}
		
		public String getCity () {
			return this.city;
		}
		
		public String getState () {
			return this.state;
		}
		
		public String getZip () {
			return this.zip;
		}
		
		public String getCountry () {
			return this.country;
		}
		
	}


