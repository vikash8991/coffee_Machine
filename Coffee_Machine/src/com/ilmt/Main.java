package com.ilmt;

import java.util.Scanner;
	public class Main {
	    private final Scanner scan;
	    private int milk;
	    private int beans;
	    private int password;

	    enum Coffee {
	        LATTE(10, 5),ESPRESSO(75, 9),CAPPUCCINO(100, 10),LONGBLACK(80,18),FILTERCOFFEE(34,30);
	        
	        private final int milk;
	        private final int beans;
	        
	        Coffee(int milk, int beans) {
	            this.milk = milk;
	            this.beans = beans;
	        }
	        public int getMilk() {
	            return this.milk;
	        }
	        public int getBeans() {
	            return this.beans;
	        }

	    }
	    private Main() {
	        this.scan = new Scanner(System.in);
	        this.milk = 540;
	        this.beans = 20;
	    }
	    public static void main(String[] args) throws InvalidChoiceException{
	        
	        Main coffeeMachine = new Main();
	        String action = coffeeMachine.checkAction();

	        while (!action.equals("exit")) {
	            action = coffeeMachine.checkAction();
	        }
	    }
	    private String checkAction() {
	        String action;
	        System.out.println("Write action (buy, fill, remaining, exit):");
	        action = scan.next();
	        System.out.println();

	        switch (action) {
	            case "buy": {
	                try {
						buyCoffee();
					} catch (InvalidChoiceException e) {
						e.printStackTrace();
					}
	                break;
	            }
	            case "fill": {
	                fillMachine();
	                break;
	            }
	            case "remaining": {
	                printState();
	                break;
	            }
	            
	        }
	        return action;
	    }

	    private void printState() {
	        System.out.println("The coffee machine has:");
	        System.out.printf("%d of milk\n", this.milk);
	        System.out.printf("%d of coffee beans\n", this.beans);
	        System.out.println();
	    }

	    private void buyCoffee() throws InvalidChoiceException{
	        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - longblack, 5 - filtercoffee back - to main menu: ");
	        String coffee = this.scan.next();

	        switch (coffee) {
	            case "back": {
	                break;
	            }
	            case "1": {
	                this.checkResources(Coffee.ESPRESSO);
	                break;
	            }
	            case "2": {
	                this.checkResources(Coffee.LATTE);
	                break;
	            }
	            case "3": {
	                this.checkResources(Coffee.CAPPUCCINO);
	                break;
	            }
	            case "4": {
	                this.checkResources(Coffee.LONGBLACK);
	                break;
	            }
	            case "5": {
	                this.checkResources(Coffee.FILTERCOFFEE);
	                break;

	            }default :{
	            throw new InvalidChoiceException();
	            }	
	            }        
	    }

	    private void fillMachine() {
	        String password;
	        try (Scanner sc = new Scanner(System.in)) {
				password=sc.nextLine();
			}
	        if("TenEleven".equals(password)){
	            System.out.println("add milk and beans");
	            
	            System.out.println("Write how many ml of milk do you want to add: ");
	            int addMilk = this.scan.nextInt();
	            System.out.println("Write how many coffee beans do you want to add: ");
	            int addBeans = this.scan.nextInt();
	            this.milk += addMilk;
	            this.beans += addBeans;
	        }
	        else{
	            System.out.println("access denied");
	        }
	        
	    }

	    private void checkResources(Coffee coffee) {
	          if (coffee.getMilk() > this.milk) {
	            System.out.println("Sorry, not enough milk!");
	        } else if (coffee.getBeans() > this.beans) {
	            System.out.println("Sorry, not enough coffee beans!");
	        } else {
	            this.beans -= coffee.getBeans();
	            this.milk -= coffee.getMilk();
	            System.out.println("I have resources, making you a coffee!");
	        }
	        System.out.println();
	    }
	}

	