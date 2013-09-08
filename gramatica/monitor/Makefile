JAVAC = javac
JAVAC_FLAGS = -sourcepath .

JAVA = java
JAVA_FLAGS =

CUP = java_cup.Main
CUPFLAGS = -nopositions -expect 100

JLEX = JLex.Main

LATEX = latex
DVIPS = dvips

all: test monitor.ps

test: absyn monitor/Yylex.class monitor/PrettyPrinter.class monitor/Test.class monitor/ComposVisitor.class monitor/AbstractVisitor.class monitor/FoldVisitor.class monitor/AllVisitor.class monitor/parser.class monitor/sym.class monitor/Test.class

.PHONY: absyn

%.class: %.java
	${JAVAC} ${JAVAC_FLAGS} $^

absyn: monitor/Absyn/Tarefa.java monitor/Absyn/ETarefa.java monitor/Absyn/Acao.java monitor/Absyn/EAcao.java monitor/Absyn/EAcao2.java monitor/Absyn/Dados.java monitor/Absyn/EDados.java monitor/Absyn/EDados1.java monitor/Absyn/EDados2.java monitor/Absyn/EDados3.java monitor/Absyn/Quantidade.java monitor/Absyn/EQuantidade.java monitor/Absyn/Operador.java monitor/Absyn/EOperador.java monitor/Absyn/Hora.java monitor/Absyn/EHora.java
	${JAVAC} ${JAVAC_FLAGS} $^

monitor/Yylex.java: monitor/Yylex
	${JAVA} ${JAVA_FLAGS} ${JLEX} monitor/Yylex

monitor/sym.java monitor/parser.java: monitor/monitor.cup
	${JAVA} ${JAVA_FLAGS} ${CUP} ${CUPFLAGS} monitor/monitor.cup
	mv sym.java parser.java monitor/

monitor/Yylex.class: monitor/Yylex.java monitor/sym.java

monitor/sym.class: monitor/sym.java

monitor/parser.class: monitor/parser.java monitor/sym.java

monitor/PrettyPrinter.class: monitor/PrettyPrinter.java

monitor.dvi: monitor.tex
	${LATEX} monitor.tex

monitor.ps: monitor.dvi
	${DVIPS} monitor.dvi -o monitor.ps

clean:
	 rm -f monitor/Absyn/*.class monitor/*.class
	 rm -f .dvi monitor.aux monitor.log monitor.ps  *.class

vclean:
	 rm -f monitor/Absyn/Tarefa.java monitor/Absyn/ETarefa.java monitor/Absyn/Acao.java monitor/Absyn/EAcao.java monitor/Absyn/EAcao2.java monitor/Absyn/Dados.java monitor/Absyn/EDados.java monitor/Absyn/EDados1.java monitor/Absyn/EDados2.java monitor/Absyn/EDados3.java monitor/Absyn/Quantidade.java monitor/Absyn/EQuantidade.java monitor/Absyn/Operador.java monitor/Absyn/EOperador.java monitor/Absyn/Hora.java monitor/Absyn/EHora.java monitor/Absyn/Tarefa.class monitor/Absyn/ETarefa.class monitor/Absyn/Acao.class monitor/Absyn/EAcao.class monitor/Absyn/EAcao2.class monitor/Absyn/Dados.class monitor/Absyn/EDados.class monitor/Absyn/EDados1.class monitor/Absyn/EDados2.class monitor/Absyn/EDados3.class monitor/Absyn/Quantidade.class monitor/Absyn/EQuantidade.class monitor/Absyn/Operador.class monitor/Absyn/EOperador.class monitor/Absyn/Hora.class monitor/Absyn/EHora.class
	 rm -f monitor/Absyn/*.class
	 rmdir monitor/Absyn/
	 rm -f monitor.tex monitor.dvi monitor.aux monitor.log monitor.ps 
	 rm -f monitor/Yylex monitor/monitor.cup monitor/Yylex.java monitor/VisitSkel.java monitor/ComposVisitor.java monitor/AbstractVisitor.java monitor/FoldVisitor.java monitor/AllVisitor.java monitor/PrettyPrinter.java monitor/Skeleton.java monitor/Test.java monitor/sym.java monitor/parser.java monitor/*.class
	 rm -f Makefile
	 rmdir -p monitor/

