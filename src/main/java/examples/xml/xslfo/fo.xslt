<?xml version="1.0" encoding="UTF-8"?>
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<fo:layout-master-set>
		<fo:simple-page-master master-name="Grundlayout"
			margin="10mm 10mm 20mm 10mm">
			<fo:region-body />
		</fo:simple-page-master>
	</fo:layout-master-set>
	<fo:page-sequence master-reference="Grundlayout">
		<fo:flow flow-name="xsl-region-body">
			<fo:block font-family="Courier" font-size="20pt" font-weight="bold" space-before="10mm"
				space-after="10mm">
				Buecherliste
			</fo:block>
			<fo:block space-before="10mm">
				<fo:inline font-weight="bold">HTML Einstieg</fo:inline>
				Bestellnummer: 13-031 Ausgabe: Gebunden
			</fo:block>
			<fo:block space-before="10mm">
				<fo:inline font-weight="bold">JavaScript Sofort</fo:inline>
				Bestellnummer: 13-038 Ausgabe: Taschenbuch
			</fo:block>
			<fo:block space-before="10mm">
				<fo:inline font-weight="bold">Java in 12 Tagen</fo:inline>
				Bestellnummer: 13-132 Ausgabe: Gebunden
			</fo:block>
			<fo:block space-before="10mm">
				<fo:inline font-weight="bold">XML fuer Anfaenger</fo:inline>
				Bestellnummer: 13-011 Ausgabe:
			</fo:block>
		</fo:flow>
	</fo:page-sequence>
</fo:root>
