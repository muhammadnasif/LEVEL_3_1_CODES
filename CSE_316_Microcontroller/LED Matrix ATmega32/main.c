/*
 * CharacterPrint_H_Offline_1.c
 *
 * Created: 4/13/2021 12:22:55 PM
 * Author : ASUS
 */ 
 
 
 
#define F_CPU 1000000UL
 
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
 
unsigned char flag = 1;
 
 
ISR(INT1_vect){
	if (flag==0)
	{
		flag = 1;
	}
	else{
		flag = 0;
	}
}
 
 
int main(void)
{
 
	unsigned char x=1;
	unsigned int i=1;
	DDRA = 0xff;
	DDRB = 0xff;
	GICR =(1<<INT1);
	MCUCR = MCUCR | 0b00001100;
	sei();
	int val_1 = 1;
	int val_2 = 2;
	int val_3 = 4;
	int val_4 = 8;	
	int a=0,b=0,c=0;
 
 
	PORTA =  0b00000001;
	PORTB = ~(0b01111100);
    while (1) 
    {
		if(flag==1){
			int k=0;
			val_1 =  val_1 << i;
			val_2 =  val_2 << i;
			val_3 =  val_3 << i;
			val_4 =  val_4 << i;
			for(k=0;k<50;k++){
				_delay_ms(2);
				
				PORTA = val_1;
				if(PORTA == 0b00000000){
					val_1 = 1;
					PORTA = val_1;
				}
				PORTB = ~(0b01111100);
				_delay_ms(2);
				
				PORTA = val_2;
				if(PORTA == 0b00000000){
					val_2 = 1;
					PORTA = val_2;
				}
				PORTB = ~(0b00010000);
				
				_delay_ms(2);
				
				PORTA = val_3;
				if(PORTA == 0b00000000){
					val_3 = 1;
					PORTA = val_3;
				}
				PORTB = ~(0b00010000);
				
				_delay_ms(2);
				
				PORTA = val_4;
				if(PORTA == 0b00000000){
					val_4 = 1;
					PORTA = val_4;
				}
				PORTB = ~(0b01111100);
			}
			_delay_ms(35);
		}
		else{
			_delay_ms(1);
			PORTA = val_1;
			PORTB = ~(0b01111100);
			_delay_ms(1);
			PORTA = val_2;
			PORTB = ~(0b00010000);
 
			_delay_ms(1);
			PORTA = val_3;
			PORTB = ~(0b00010000);
 
			_delay_ms(1);
			PORTA = val_4;
			PORTB = ~(0b01111100);
		}						
	}
}
 
 
 
/*
#include <avr/io.h>
#include <avr/interrupt.h> //STEP1
 
ISR(INT1_vect)//STEP2
{
	PORTB = ~PORTB;
}
int main(void)
{
	DDRB = 0xFF;
	PORTB = 0b01010101;
	GICR = (1<<INT1); //STEP3
	MCUCR = MCUCR & 0b11110011;//STEP4
	sei();//STEP5
    while(1);
}
*/