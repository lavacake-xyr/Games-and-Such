import java.util.Scanner;

public class TurnBasedRPG{
	public static void main(String []args){
		Scanner in = new Scanner(System.in);
		
		// STATS
		int playerHp=100, playerShield=50, playerDMG=15;
		int enemyHp=100, enemyShield=25, enemyDMG=18;

		//Items quantity
		int redPotQty=2, bluePotQty=4;
		
		//QOL variables
		int postDMG;
		int remainHp;
		int action;
		int round=1;
	
		
		// This string is the variable for the game to start.
		String gameStart;
		
		//This loop asks if the player chooses to start the game.
		do{	
			System.out.println("Do you want to start?");
			System.out.print("Type [Y] for yes, [N] for no: ");
			gameStart=in.next();

			if(gameStart.equalsIgnoreCase("n")){
				System.out.println("Closing the game...");
				in.close();
				return;
			}else if (!gameStart.equalsIgnoreCase("y")){
				System.out.println("----------------------------------");
				System.out.println("Invalid option, please try again.");
				
			}else if(gameStart.equalsIgnoreCase("y")){
				System.out.println("----------------------------------");
				System.out.println("Starting Game...");
			}
		}while(!gameStart.equalsIgnoreCase("y"));
		
		//This loop is the turn based fighting part.
		do{
			System.out.println("-------------ROUND " + round + "--------------");
			
			System.out.println("Your HP:  " + playerHp + "[" + playerShield + "] | " + "Your DMG:  " + playerDMG);
			System.out.println("Enemy HP: " + enemyHp + "[" + enemyShield + "] | " + "Enemy DMG: " + enemyDMG);
			System.out.println();
			System.out.print("Enter [1] to attack, enter [2] to use item: ");
			action=in.nextInt();
			
			//Player chooses option [1]
			if(action==1){
				//Player Atks the enemy
				remainHp = Math.min(enemyShield, playerDMG);
    			enemyShield -= remainHp;

				postDMG = playerDMG - remainHp;
    			enemyHp = Math.max(0, enemyHp - postDMG);

				System.out.println("----------------------------------");
				System.out.println("You dealt " + playerDMG + " damage to the enemy, leaving them with " + enemyHp + " HP and " + enemyShield + " Shield.");
				System.out.println();
				
				//Checks if the player or enemy is dead
				if(playerHp==100&&playerShield==50){
					if(enemyHp==0&&enemyShield==0){
						if(playerDMG==1500){
						}
						System.out.println("----------------------------------");
						System.out.println("Flawless Victory!");
						break;
					}
				}else if(playerHp<=0){
				System.out.println("----------------------------------");
					System.out.println("You lost sorri :c");
					break;
				
				}else if(enemyHp<=0){
					System.out.println("----------------------------------");
					System.out.println("You Won!");
					break;
				}
				
				//Enemy attacks the player
				remainHp = Math.min(playerShield, enemyDMG);
				playerShield -= remainHp;

				postDMG = enemyDMG - remainHp;
				playerHp = Math.max(0, playerHp - postDMG);
				
				System.out.println("----------------------------------");
				System.out.println("and the enemy dealt " + enemyDMG + " damage to you, leaving you with " + playerHp + " HP and " + playerShield + " Shield.");
				System.out.println();

			}else if (action==2){
				//Player chooses an item
				System.out.println("What item do you want to use?");
				System.out.println("Red Potion: " + redPotQty + " | Blue Potion: " + bluePotQty);
				System.out.print("[1] Red Potion (+20 HP) or [2] Blue Potion (+10 Shield): ");
				action=in.nextInt();
				if(action==1&&redPotQty>0){
					playerHp+=20;
					redPotQty--;
					if(playerHp>100){
						playerHp=100;
					}
					System.out.println("----------------------------------");
					System.out.println("You healed 20 HP!");
				}else if(action==2&&bluePotQty>0){
					playerShield+=10;
					bluePotQty--;
					if(playerShield>50){
						playerShield=50;
					}
					System.out.println("----------------------------------");
					System.out.println("You gained 10 Shield!");
				}else{
					System.out.println("----------------------------------");
					System.out.println("No more pots :C");
				}
				//CHEATCODES
			}else if(action==67){
				String cheatcode;
				System.out.println("----------------------------------");
				System.out.print("Enter a cheatcode: ");
				cheatcode=in.next();
				if(cheatcode.equals("SAITAMA")){
					playerDMG *= 10;
				}else if(cheatcode.equals("BULWARK")){
					playerShield *= 2;
				}else if(cheatcode.equals("GODMODE")){
					playerHp *= 100;
					playerDMG *= 100;
					playerShield *= 100;
				}else{
					System.out.println("----------------------------------");
					System.out.println("Invalid cheatcode.");
				}
			}
			round++;
			if(action!=1&&action!=2&&action!=67){
				System.out.println("----------------------------------");
				System.out.println("Invalid option, please try again.");
			}
		}while(playerHp>0&&enemyHp>0);
	
		in.close();
	}
}