all: negatif.pdf negatifSixMois.pdf suspensSixMois.pdf mostlyclean

negatif.pdf: negatif.tex
	pdflatex negatif.tex

negatifSixMois.pdf: negatifSixMois.tex
	pdflatex negatifSixMois.tex

suspensSixMois.pdf: suspensSixMois.tex
	pdflatex suspensSixMois.tex

mostlyclean:
	rm -rf *.log *.aux

clean:
	rm -rf negatif.pdf negatifSixMois.pdf suspensSixMois.pdf *.log *.aux

rebuild: clean all
