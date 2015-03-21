# Introduction to JSimLife #

# How does it Work #

To understand how JSimLife work you simply read the live() method of the Sprite.

**Zlife live methods**
```


if(isAlive())
 {
    switch(state)
	{
	  case BORED:
		{
		  if(energy>hungryEnergy && energy<hornyEnergy)
			{
				setSpeed((int) getBoredSpeed());
				break; 
			}
					
		if(energy<hungryEnergy)
			{   age();
			    setSpeed((int) getHungrySpeed());
			    state =  CellState.HUNGRY;
			    break;
		        }
					
		if(energy>hornyEnergy)
			{
			  age();
			setSpeed((int) getHornySpeed());
			state =  CellState.HORNY;
			 break;
			}
					
		}
	  case HUNGRY:
			{
			  if (energy < hungryEnergy)
				{
				if(seedPosition.getX()==0 && seedPosition.getY()==0)
				   {
				this.setSpeed(  (int) ((BoredSpeed + hungrySpeed)/2 ));
				   break;
				   }
				else
				  {
				    this.setSpeed((int)getHungrySpeed());
				    this.moveTo(getSeedPosition());
				    seedPosition.setX(0);
				    seedPosition.setY(0);
				    break;
				   }
			        }
			 if (energy > hungryEnergy)
				{ 
			          this.setSpeed((int)getBoredSpeed());
				  state = CellState.BORED;
				 this.setAngleRadians(Math.random()*Math.PI*2);
				 break;
			        }  
					
			}
		case HORNY:
			{
			  if(energy>hornyEnergy)
			     {
				this.setSpeed((int)getHornySpeed());
				break;
			      }
			 if(energy<hornyEnergy && energy>hungryEnergy)
					{
					this.setSpeed((int)getBoredSpeed());
					state = CellState.BORED;
		    		        this.setAngleRadians(Math.random()*Math.PI*2);
					break;
					}
			 if(energy<hungryEnergy)
					{
					age();
					 this.setSpeed((int)getHungrySpeed());
					state = CellState.HUNGRY;
					this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
			}
				
		case SCARY:
			{   		
			 this.setSpeed((int)scarySpeed);		
			    if ((int)energy == (int)((hungryEnergy)*1.5))
				 this.setAngleRadians(Math.random()*Math.PI*2);
					
			     if(energy< (hungryEnergy))
					{
					age();
					this.setSpeed((int)getHungrySpeed());
					state = CellState.HUNGRY;
					this.setAngleRadians(Math.random()*Math.PI*2);
						break;
					}
					
				}
			
		}
		else
			{
			setAlive(false);
			
			}
	}

```

**Zredator live() methods**

```

if(isAlive())
	{
	   switch(state)
		{
		case BORED:
			{
			if(energy>hungryEnergy && energy<hornyEnergy)
			     {
				setSpeed((int) getBoredSpeed()+1);
				break; //this.move();
			      }
			if(energy<hungryEnergy)
			      {   age();
				  setSpeed((int) getHungrySpeed()+2);
				  state =  CellState.HUNGRY;
				  this.setAngleRadians(Math.random()*Math.PI*2);
				  break;
			      }
					
			if(energy>hornyEnergy)
			     {
				age();
				setSpeed((int) getHornySpeed()+3);
				state =  CellState.HORNY;
				this.setAngleRadians(Math.random()*Math.PI*2);
				break;
			     }
					
			 }
		case HUNGRY:
			{
			   this.setSpeed((int)hungrySpeed + 4);
					
			   if (energy < hungryEnergy)
				{
				   break;
				}
					
			   if (energy > hungryEnergy)
				 {
				   this.age();
				   this.setSpeed((int)getBoredSpeed()+5);
				   state = CellState.BORED;
				   this.setAngleRadians(Math.random()*Math.PI*2);
				    break;
				 }  
					
			}
		case HORNY:
			{
			  this.setSpeed((int)getHornySpeed());
			  if(energy>hornyEnergy)
			        {
				 break;
			        }
			if(energy<hornyEnergy && energy>hungryEnergy)
				{
				this.setSpeed((int)getBoredSpeed());
				state = CellState.BORED;
				this.setAngleRadians(Math.random()*Math.PI*2);
				break;
                        if(energy<hungryEnergy)
				{
				  age();
				  this.setSpeed((int)getHungrySpeed()+6);
				  state = CellState.HUNGRY;
				  this.setAngleRadians(Math.random()*Math.PI*2);
				  break;
				}
			}
				
		case SCARY:
			{   
			   state = CellState.BORED;
			   break;
			}
				
		}
	}
	else
	{
		setAlive(false);
			
	}

```

## Riproducing ##
When Zlife or Zredator are horny, they are able to riproduce itself. When they meet an other horny zlife they spend a HornyEnergy quantity. So, riducing this parameter increase the number of sons born on a single "love act"! The new born zlife has got a 20 energy starting point.

## Eating ##
When a Zlife is hungry, it movee in search of food. Zlifes eating seeds, but Zredator eat Zlife. When a ZLife passes beside a Zredator, is the end for it, cause Zredator eats it. But when a ZLife dies, it becomes a seed to close the food chain!
When a Zredator eat, his energe increase to a value equal to five times the Zlife radius.

# Details #