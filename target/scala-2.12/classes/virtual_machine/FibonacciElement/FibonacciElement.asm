	@256
	D=A
	@SP
	M=D
	@RETURN_LABEL0
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@LCL
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@ARG
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THIS
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THAT
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	D=M
	@5
	D=D-A
	@0
	D=D-A
	@ARG
	M=D
	@SP
	D=M
	@LCL
	M=D
	@Sys.init
	0;JMP
(RETURN_LABEL0)
(Main.fibonacci)
	@0
	D=A
	@ARG
	A=M+D
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@2
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	M=M-1
	A=M
	D=M
	@SP
	M=M-1
	A=M
	D=M-D
	@TRUE_1
	D;JLT
	@SP
	A=M
	M=0
	@UPDATE_SP_1
	0;JMP
(TRUE_1)
	@SP
	A=M
	M=-1
(UPDATE_SP_1)
	@SP
	M=M+1
	@SP
	AM=M-1
	D=M
	@Main.fibonacci$IF_TRUE
	D;JNE
	@Main.fibonacci$IF_FALSE
	0;JMP
(Main.fibonacci$IF_TRUE)
	@0
	D=A
	@ARG
	A=M+D
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@LCL
	D=M
	@FRAME
	M=D
	@5
	A=D-A
	D=M
	@RET
	M=D
	@ARG
	D=M
	@0
	D=D+A
	@R13
	M=D
	@SP
	A=M-1
	D=M
	@SP
	M=M-1
	@R13
	A=M
	M=D
	@ARG
	D=M
	@SP
	M=D+1
	@1
	D=A
	@FRAME
	A=M-D
	D=M
	@THAT
	M=D
	@2
	D=A
	@FRAME
	A=M-D
	D=M
	@THIS
	M=D
	@3
	D=A
	@FRAME
	A=M-D
	D=M
	@ARG
	M=D
	@4
	D=A
	@FRAME
	A=M-D
	D=M
	@LCL
	M=D
	@RET
	A=M
	0;JMP
(Main.fibonacci$IF_FALSE)
	@0
	D=A
	@ARG
	A=M+D
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@2
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	M=M-1
	A=M
	D=M
	@SP
	M=M-1
	A=M
	M=M-D
	@SP
	M=M+1
	@RETURN_LABEL2
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@LCL
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@ARG
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THIS
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THAT
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	D=M
	@5
	D=D-A
	@1
	D=D-A
	@ARG
	M=D
	@SP
	D=M
	@LCL
	M=D
	@Main.fibonacci
	0;JMP
(RETURN_LABEL2)
	@0
	D=A
	@ARG
	A=M+D
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@1
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	M=M-1
	A=M
	D=M
	@SP
	M=M-1
	A=M
	M=M-D
	@SP
	M=M+1
	@RETURN_LABEL3
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@LCL
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@ARG
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THIS
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THAT
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	D=M
	@5
	D=D-A
	@1
	D=D-A
	@ARG
	M=D
	@SP
	D=M
	@LCL
	M=D
	@Main.fibonacci
	0;JMP
(RETURN_LABEL3)
	@SP
	M=M-1
	A=M
	D=M
	@SP
	M=M-1
	A=M
	M=M+D
	@SP
	M=M+1
	@LCL
	D=M
	@FRAME
	M=D
	@5
	A=D-A
	D=M
	@RET
	M=D
	@ARG
	D=M
	@0
	D=D+A
	@R13
	M=D
	@SP
	A=M-1
	D=M
	@SP
	M=M-1
	@R13
	A=M
	M=D
	@ARG
	D=M
	@SP
	M=D+1
	@1
	D=A
	@FRAME
	A=M-D
	D=M
	@THAT
	M=D
	@2
	D=A
	@FRAME
	A=M-D
	D=M
	@THIS
	M=D
	@3
	D=A
	@FRAME
	A=M-D
	D=M
	@ARG
	M=D
	@4
	D=A
	@FRAME
	A=M-D
	D=M
	@LCL
	M=D
	@RET
	A=M
	0;JMP
(Sys.init)
	@4
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@RETURN_LABEL4
	D=A
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@LCL
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@ARG
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THIS
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@THAT
	D=M
	@SP
	A=M
	M=D
	@SP
	M=M+1
	@SP
	D=M
	@5
	D=D-A
	@1
	D=D-A
	@ARG
	M=D
	@SP
	D=M
	@LCL
	M=D
	@Main.fibonacci
	0;JMP
(RETURN_LABEL4)
(Sys.init$WHILE)
	@Sys.init$WHILE
	0;JMP