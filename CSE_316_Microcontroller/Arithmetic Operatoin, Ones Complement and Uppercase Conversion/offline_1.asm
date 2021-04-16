.MODEL SMALL

.STACK 100H

.DATA      
    CR EQU 0DH
    LF EQU 0AH       
    
    
    INPUT_X DB CR,LF, 'INPUT X: $'
    INPUT_Y DB CR,LF, 'INPUT Y: $'
    
    OUTPUT_1 DB CR,LF, 'X - 2Y => $'
    OUTPUT_2 DB CR,LF, '25 - ( X+Y ) => $'
    OUTPUT_3 DB CR,LF, '2X - 3Y => $'    
    OUTPUT_4 DB CR,LF, 'Y - X + 1 => $'
    
    
    BR DB CR, LF, '$'
    
    X DB ?
    Y DB ?
    Z DB ?
    FIX DB -48

.CODE 


MAIN PROC  

;HEADER FILE
    MOV AX,@DATA
    MOV DS,AX    


;STRING SHOW
    LEA DX,INPUT_X
    MOV AH,9
    INT 21H


;INPUT X
    MOV AH,1
    INT 21H 
    ADD AL,FIX
    MOV X,AL
    

;STRING SHOW
    LEA DX,INPUT_Y
    MOV AH,9
    INT 21H
    

;INPUT Y
    MOV AH,1
    INT 21H 
    ADD AL,FIX
    MOV Y,AL     

;STRING SHOW
    LEA DX,BR
    MOV AH,9
    INT 21H


    
;TASK_1
    MOV AH,Y
    ADD AH,AH
    NEG AH
    ADD AH,X
    MOV Z,AH
;OUTPUT_1 SHOW
    LEA DX,OUTPUT_1
    MOV AH,9
    INT 21H
    
    MOV AH,2
    ADD Z,48
    MOV DL,Z
    INT 21H           
                
;TAKS_2
    MOV AH,X
    ADD AH,Y
    NEG AH
    ADD AH,25
    MOV Z,AH      
;OUTPUT_2 SHOW
    LEA DX,OUTPUT_2
    MOV AH,9
    INT 21H
    
    MOV AH,2
    ADD Z,48
    MOV DL,Z
    INT 21H
    
    
;TASK_3
    MOV AH,Y
    ADD AH,Y
    ADD AH,Y
    NEG AH
    ADD AH,X
    ADD AH,X                
    MOV Z,AH
    
;OUTPUT_3 SHOW
    LEA DX,OUTPUT_3
    MOV AH,9
    INT 21H
    
    MOV AH,2
    ADD Z,48
    MOV DL,Z
    INT 21H
    
    
;TASK_4
    MOV AH,X
    NEG AH
    ADD AH,Y
    ADD AH,1
    MOV Z,AH
               
;OUTPUT_4 SHOW
    LEA DX,OUTPUT_4
    MOV AH,9
    INT 21H
    
    MOV AH,2
    ADD Z,48
    MOV DL,Z
    INT 21H
    
        
    
;RETURN 0
    MOV AH,4CH
    INT 21H
    
MAIN ENDP
    END MAIN          