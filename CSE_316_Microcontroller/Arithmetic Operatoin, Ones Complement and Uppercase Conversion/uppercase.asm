.MODEL SMALL

.STACK 100H

.DATA      
    CR EQU 0DH
    LF EQU 0AH       
    
    
    INPUT DB CR,LF, 'UPPERCASE LETTER : $'
    
    
    BR DB CR, LF, '$'
    
    X DB ?
    Y DB ?
    Z DB ?

.CODE 


MAIN PROC  

;HEADER FILE
    MOV AX,@DATA
    MOV DS,AX    


;STRING SHOW
    LEA DX,INPUT
    MOV AH,9
    INT 21H


;INPUT X
    MOV AH,1
    INT 21H 
    MOV X,AL
        

;STRING SHOW
    LEA DX,BR
    MOV AH,9
    INT 21H

;TASK_1
    MOV AH,X
    DEC AH
    ADD AH,32
    MOV Z,AH
    

    



               
;OUTPUT_4 SHOW
    ;LEA DX,OUTPUT_4
    ;MOV AH,9
    ;INT 21H
    
    MOV AH,2
    MOV DL,Z
    INT 21H
    
        
    
;RETURN 0
    MOV AH,4CH
    INT 21H
    
MAIN ENDP
    END MAIN          