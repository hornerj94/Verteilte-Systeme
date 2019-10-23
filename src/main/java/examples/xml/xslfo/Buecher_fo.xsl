<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	<xsl:template match="Buecher">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="Grundlayout"
					margin="10mm 10mm 20mm 10mm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="Grundlayout">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-family="Courier" font-size="20pt" font-weight="bold"
						space-before="10mm" space-after="10mm">
						Buecherliste
					</fo:block>
					<xsl:apply-templates select="Buch" />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template match="Buch">
		<fo:block space-before="10mm">
			<fo:inline font-weight="bold">
				<xsl:value-of select="Titel" />
			</fo:inline>
			<xsl:text>  Bestellnummer: </xsl:text>
			<xsl:value-of select="Bestellnummer" />
			<xsl:text>  Ausgabe: </xsl:text>
			<xsl:value-of select="@Ausgabe" />
		</fo:block>
	</xsl:template>
</xsl:stylesheet>
