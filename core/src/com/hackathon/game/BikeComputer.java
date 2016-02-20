package com.hackathon.game;

public class BikeComputer {
	private final double M_PER_MI = 1609.344; // mm are in a mile
	private final int M_PER_KM = 1000; // m in a kilometer
	private final int MS_PER_S = 1000; // Milliseconds in a second
	private final int S_PER_HOUR = 3600; // Seconds in an hour
	private double wheelCircumference; // How many mm around your wheel?
	private int clicks; // How many clicks have we had?
	private int newClicks; // How many clicks have happened since the last time we updated?
	private double distance; // How far have we gone ever?
	private long startTime; // When did we start?
	private long lastTime; // What was the timestamp of the last click?
	private long elapsedTime; // How much time have we spent riding?
	private double instSpeed; // mm/ms
	private double avgSpeed; // mm/ms
	private double maxSpeed; // mm/ms
	private boolean metric;  
	
	public void update() {
		double instSpeed = 0;
		double distDelta = this.newClicks * this.wheelCircumference; // mm since last update
		
		long currentTime = System.currentTimeMillis();
		long timeDelta = (currentTime - this.lastTime); // ms since last update
		
		if(newClicks > 0) {
			instSpeed = (distDelta / timeDelta ); // mm per ms (or m/s)
		}
		
		this.instSpeed = instSpeed;
		this.distance += distDelta;
		this.elapsedTime += timeDelta;
		this.avgSpeed = this.distance / this.elapsedTime;
		this.maxSpeed = Math.max(this.maxSpeed,this.instSpeed);
		this.clicks += this.newClicks;
		this.newClicks = 0;
		this.lastTime = currentTime;
	}
	public void display() {
		System.out.println("D: "+this.getDistance()/1000+" m");
		System.out.println("T: "+this.getElapsedTime()/1000+" s");
		System.out.println("S: "+this.getInstSpeed()+" m/s");
		System.out.println("A: "+this.getAvgSpeed()+" m/s");
		System.out.println("M: "+this.getMaxSpeed()+" m/s");
		System.out.println("DS: "+this.getDisplayInstSpeed()+" "+this.getUnits());
		System.out.println("DA: "+this.getDisplayAvgSpeed()+" "+this.getUnits());
		System.out.println("DM: "+this.getDisplayMaxSpeed()+" "+this.getUnits());

		System.out.println(" ");
	}
	public void click() {
		// Increment the clicks
		this.newClicks += 1;
	}
	
	public void reset() {
		this.clicks = 0;
		this.newClicks = 0;
		this.instSpeed = 0;
		this.avgSpeed = 0;
		this.maxSpeed = 0;
		this.distance = 0;
		this.startTime = System.currentTimeMillis();
		this.lastTime = this.startTime;
		this.elapsedTime = 0;
	}
	
	public double getDisplayInstSpeed() {
		double displayInstSpeed;
		if(this.metric) {
			displayInstSpeed = (this.instSpeed * this.S_PER_HOUR) / this.M_PER_KM;
		} else {
			displayInstSpeed = (this.instSpeed * this.S_PER_HOUR) / this.M_PER_MI;
		}
		return (double) Math.round(displayInstSpeed * 100) / 100;
	}

	public double getDisplayAvgSpeed() {
		double displayAvgSpeed;
		if(this.metric) {
			displayAvgSpeed = (this.avgSpeed * this.S_PER_HOUR) / this.M_PER_KM;
		} else {
			displayAvgSpeed = (this.avgSpeed * this.S_PER_HOUR) / this.M_PER_MI;
		}
		return (double) Math.round(displayAvgSpeed * 100) / 100;
	}
	
	public double getDisplayMaxSpeed() {
		double displayMaxSpeed;
		if(this.metric) {
			displayMaxSpeed = (this.maxSpeed * this.S_PER_HOUR) / this.M_PER_KM;
		} else {
			displayMaxSpeed = (this.maxSpeed * this.S_PER_HOUR) / this.M_PER_MI;
		}
		return (double) Math.round(displayMaxSpeed * 100) / 100;	
	}
	
	/* Constructor, getters, setters, and other bullshit here */
	
	public BikeComputer() {
		this.wheelCircumference = 2130.00; // Defaults to 700c wheel with 28mm tires.
		this.instSpeed = 0;
		this.avgSpeed = 0;
		this.maxSpeed = 0;
		this.metric = false; // 'Murikuh!
		this.distance = 0;
		this.clicks = 0;
		this.newClicks = 0;
		this.startTime = System.currentTimeMillis();
		this.lastTime = this.startTime;
		this.elapsedTime = 0;
	}
	
	public double getWheelCircumference() {
		return wheelCircumference;
	}

	public void setWheelCircumference(double wheelCircumference) {
		this.wheelCircumference = wheelCircumference;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public double getInstSpeed() {
		return instSpeed;
	}

	public void setInstSpeed(float instSpeed) {
		this.instSpeed = instSpeed;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(float avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public boolean isMetric() {
		return metric;
	}

	public void setMetric(boolean metric) {
		this.metric = metric;
	}

	public String getUnits() {
		if (this.metric) {
			return "kmph";
		} else {
			return "mph";
		}
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
