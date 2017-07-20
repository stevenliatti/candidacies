all: test.pdf negatif.pdf negatifSixMois.pdf suspensSixMois.pdf mostlyclean

test.pdf: test.tex
	pdflatex test.tex

negatif.pdf: negatif.tex preamble.tex header.tex footer.tex
	pdflatex negatif.tex

negatifSixMois.pdf: negatifSixMois.tex preamble.tex header.tex footer.tex
	pdflatex negatifSixMois.tex

suspensSixMois.pdf: suspensSixMois.tex preamble.tex header.tex footer.tex
	pdflatex suspensSixMois.tex

mostlyclean:
	rm -rf *.log *.aux

clean:
	rm -rf test.* negatif.pdf negatifSixMois.pdf suspensSixMois.pdf *.log *.aux

rebuild: clean all
