JAVAC = javac
JAVAC_FLAGS = -sourcepath .

JAVA = java
JAVA_FLAGS =

CUP = java_cup.Main
CUPFLAGS = -nopositions -expect 100

JLEX = JLex.Main

LATEX = latex
DVIPS = dvips

all: test atuador.ps

test: absyn atuador/Yylex.class atuador/PrettyPrinter.class atuador/Test.class atuador/ComposVisitor.class atuador/AbstractVisitor.class atuador/FoldVisitor.class atuador/AllVisitor.class atuador/parser.class atuador/sym.class atuador/Test.class

.PHONY: absyn

%.class: %.java
	${JAVAC} ${JAVAC_FLAGS} $^

absyn: atuador/Absyn/Tarefa.java atuador/Absyn/ETarefa.java atuador/Absyn/Acao.java atuador/Absyn/EAcao.java atuador/Absyn/EAcao1.java atuador/Absyn/Remedio.java atuador/Absyn/ERemedio.java atuador/Absyn/ERemedio1.java
	${JAVAC} ${JAVAC_FLAGS} $^

atuador/Yylex.java: atuador/Yylex
	${JAVA} ${JAVA_FLAGS} ${JLEX} atuador/Yylex

atuador/sym.java atuador/parser.java: atuador/atuador.cup
	${JAVA} ${JAVA_FLAGS} ${CUP} ${CUPFLAGS} atuador/atuador.cup
	mv sym.java parser.java atuador/

atuador/Yylex.class: atuador/Yylex.java atuador/sym.java

atuador/sym.class: atuador/sym.java

atuador/parser.class: atuador/parser.java atuador/sym.java

atuador/PrettyPrinter.class: atuador/PrettyPrinter.java

atuador.dvi: atuador.tex
	${LATEX} atuador.tex

atuador.ps: atuador.dvi
	${DVIPS} atuador.dvi -o atuador.ps

clean:
	 rm -f atuador/Absyn/*.class atuador/*.class
	 rm -f .dvi atuador.aux atuador.log atuador.ps  *.class

vclean:
	 rm -f atuador/Absyn/Tarefa.java atuador/Absyn/ETarefa.java atuador/Absyn/Acao.java atuador/Absyn/EAcao.java atuador/Absyn/EAcao1.java atuador/Absyn/Remedio.java atuador/Absyn/ERemedio.java atuador/Absyn/ERemedio1.java atuador/Absyn/Tarefa.class atuador/Absyn/ETarefa.class atuador/Absyn/Acao.class atuador/Absyn/EAcao.class atuador/Absyn/EAcao1.class atuador/Absyn/Remedio.class atuador/Absyn/ERemedio.class atuador/Absyn/ERemedio1.class
	 rm -f atuador/Absyn/*.class
	 rmdir atuador/Absyn/
	 rm -f atuador.tex atuador.dvi atuador.aux atuador.log atuador.ps 
	 rm -f atuador/Yylex atuador/atuador.cup atuador/Yylex.java atuador/VisitSkel.java atuador/ComposVisitor.java atuador/AbstractVisitor.java atuador/FoldVisitor.java atuador/AllVisitor.java atuador/PrettyPrinter.java atuador/Skeleton.java atuador/Test.java atuador/sym.java atuador/parser.java atuador/*.class
	 rm -f Makefile
	 rmdir -p atuador/

