JAVAC = javac
JAVAC_FLAGS = -sourcepath .

JAVA = java
JAVA_FLAGS =

CUP = java_cup.Main
CUPFLAGS = -nopositions -expect 100

JLEX = JLex.Main

LATEX = latex
DVIPS = dvips

all: test Centralizador.ps

test: absyn Centralizador/Yylex.class Centralizador/PrettyPrinter.class Centralizador/Test.class Centralizador/ComposVisitor.class Centralizador/AbstractVisitor.class Centralizador/FoldVisitor.class Centralizador/AllVisitor.class Centralizador/parser.class Centralizador/sym.class Centralizador/Test.class

.PHONY: absyn

%.class: %.java
	${JAVAC} ${JAVAC_FLAGS} $^

absyn: Centralizador/Absyn/Tarefa.java Centralizador/Absyn/ETask.java Centralizador/Absyn/Acao.java Centralizador/Absyn/EAction1.java Centralizador/Absyn/EAction2.java Centralizador/Absyn/EAction3.java Centralizador/Absyn/Coletar.java Centralizador/Absyn/ECollect1.java Centralizador/Absyn/ECollect2.java Centralizador/Absyn/Aplicar.java Centralizador/Absyn/EApply1.java Centralizador/Absyn/EApply2.java Centralizador/Absyn/Dados.java Centralizador/Absyn/EData1.java Centralizador/Absyn/EData2.java Centralizador/Absyn/EData3.java Centralizador/Absyn/EData4.java Centralizador/Absyn/EData5.java Centralizador/Absyn/Operador.java Centralizador/Absyn/EOp.java Centralizador/Absyn/Medicacao.java Centralizador/Absyn/EMedic1.java Centralizador/Absyn/EMedic2.java Centralizador/Absyn/Quantidade.java Centralizador/Absyn/EQtde1.java Centralizador/Absyn/Remedio.java Centralizador/Absyn/ERemedy1.java Centralizador/Absyn/ERemedy2.java
	${JAVAC} ${JAVAC_FLAGS} $^

Centralizador/Yylex.java: Centralizador/Yylex
	${JAVA} ${JAVA_FLAGS} ${JLEX} Centralizador/Yylex

Centralizador/sym.java Centralizador/parser.java: Centralizador/Centralizador.cup
	${JAVA} ${JAVA_FLAGS} ${CUP} ${CUPFLAGS} Centralizador/Centralizador.cup
	mv sym.java parser.java Centralizador/

Centralizador/Yylex.class: Centralizador/Yylex.java Centralizador/sym.java

Centralizador/sym.class: Centralizador/sym.java

Centralizador/parser.class: Centralizador/parser.java Centralizador/sym.java

Centralizador/PrettyPrinter.class: Centralizador/PrettyPrinter.java

Centralizador.dvi: Centralizador.tex
	${LATEX} Centralizador.tex

Centralizador.ps: Centralizador.dvi
	${DVIPS} Centralizador.dvi -o Centralizador.ps

clean:
	 rm -f Centralizador/Absyn/*.class Centralizador/*.class
	 rm -f .dvi Centralizador.aux Centralizador.log Centralizador.ps  *.class

vclean:
	 rm -f Centralizador/Absyn/Tarefa.java Centralizador/Absyn/ETask.java Centralizador/Absyn/Acao.java Centralizador/Absyn/EAction1.java Centralizador/Absyn/EAction2.java Centralizador/Absyn/EAction3.java Centralizador/Absyn/Coletar.java Centralizador/Absyn/ECollect1.java Centralizador/Absyn/ECollect2.java Centralizador/Absyn/Aplicar.java Centralizador/Absyn/EApply1.java Centralizador/Absyn/EApply2.java Centralizador/Absyn/Dados.java Centralizador/Absyn/EData1.java Centralizador/Absyn/EData2.java Centralizador/Absyn/EData3.java Centralizador/Absyn/EData4.java Centralizador/Absyn/EData5.java Centralizador/Absyn/Operador.java Centralizador/Absyn/EOp.java Centralizador/Absyn/Medicacao.java Centralizador/Absyn/EMedic1.java Centralizador/Absyn/EMedic2.java Centralizador/Absyn/Quantidade.java Centralizador/Absyn/EQtde1.java Centralizador/Absyn/Remedio.java Centralizador/Absyn/ERemedy1.java Centralizador/Absyn/ERemedy2.java Centralizador/Absyn/Tarefa.class Centralizador/Absyn/ETask.class Centralizador/Absyn/Acao.class Centralizador/Absyn/EAction1.class Centralizador/Absyn/EAction2.class Centralizador/Absyn/EAction3.class Centralizador/Absyn/Coletar.class Centralizador/Absyn/ECollect1.class Centralizador/Absyn/ECollect2.class Centralizador/Absyn/Aplicar.class Centralizador/Absyn/EApply1.class Centralizador/Absyn/EApply2.class Centralizador/Absyn/Dados.class Centralizador/Absyn/EData1.class Centralizador/Absyn/EData2.class Centralizador/Absyn/EData3.class Centralizador/Absyn/EData4.class Centralizador/Absyn/EData5.class Centralizador/Absyn/Operador.class Centralizador/Absyn/EOp.class Centralizador/Absyn/Medicacao.class Centralizador/Absyn/EMedic1.class Centralizador/Absyn/EMedic2.class Centralizador/Absyn/Quantidade.class Centralizador/Absyn/EQtde1.class Centralizador/Absyn/Remedio.class Centralizador/Absyn/ERemedy1.class Centralizador/Absyn/ERemedy2.class
	 rm -f Centralizador/Absyn/*.class
	 rmdir Centralizador/Absyn/
	 rm -f Centralizador.tex Centralizador.dvi Centralizador.aux Centralizador.log Centralizador.ps 
	 rm -f Centralizador/Yylex Centralizador/Centralizador.cup Centralizador/Yylex.java Centralizador/VisitSkel.java Centralizador/ComposVisitor.java Centralizador/AbstractVisitor.java Centralizador/FoldVisitor.java Centralizador/AllVisitor.java Centralizador/PrettyPrinter.java Centralizador/Skeleton.java Centralizador/Test.java Centralizador/sym.java Centralizador/parser.java Centralizador/*.class
	 rm -f Makefile
	 rmdir -p Centralizador/

