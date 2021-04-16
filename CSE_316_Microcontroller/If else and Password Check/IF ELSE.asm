.MODEL SMALL

.STACK 100H

.DATA
    CR EQU 0DH
    LF EQU 0AH
    
    BR DB CR,LF, '$'
    SPACE DB ' $'
    EQUAL_STRING DB CR,LF, 'All the numbers are equal$'
    
    X DB ?
    Y DB ?
    Z DB ?
    RESULT DB ?
    INTER DB ?         
  
.CODE

MAIN PROC
    MOV AX,@DATA
    MOV DS,AX
    
    MOV AH,1
    
    INT 21H          ;INPUT X
    MOV X,AL
    
    MOV AH,9
    LEA DX,SPACE
    INT 21H        
     
    MOV AH,1                  
    INT 21H          ;INPUT Y
    MOV Y,AL
    
    MOV AH,9
    LEA DX,SPACE
    INT 21H    
    
    MOV AH,1 
    INT 21H          ;INPUT Z
    MOV Z,AL     
    
    MOV AH,9
    LEA DX,BR
    INT 21H
    MOV AH,1
        
            
    MOV BH,X
    MOV CH,Y
    MOV DH,Z        
    
    MOV AH,2

IF:    
    CMP DH,BH
    JA  ELSE


THEN:    
    CMP BH,CH
    JB  STATEMENT_1
    CMP DH,CH
    JB STATEMENT_2
    CMP CH,DH
    JB STATEMENT_3
    JMP DISPLAY_EQUAL

STATEMENT_1:
    MOV RESULT,BH   ;BH -> b
    JMP DISPLAY_RESULT

STATEMENT_2:
    MOV RESULT,CH   ;CH -> c
    JMP DISPLAY_RESULT
    
STATEMENT_3:
    MOV RESULT,DH   ;DH -> a
    JMP DISPLAY_RESULT
              
ELSE:
    CMP DH,CH
    JB STATEMENT_3
    CMP BH,CH
    JB STATEMENT_2
    CMP CH,BH
    JB STATEMENT_1                  
    JMP DISPLAY_EQUAL

DISPLAY_EQUAL:
    LEA DX,EQUAL_STRING
    MOV AH,9
    INT 21H
    
           
DISPLAY_RESULT:
    MOV DL,RESULT
    INT 21H
           
    
    MOV AH,4CH
    INT 21H
    
MAIN ENDP
    END MAIN



