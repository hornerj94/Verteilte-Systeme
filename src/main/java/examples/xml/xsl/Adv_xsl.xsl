<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<HTML>
			<HEAD>
				<TITLE>Buecher - Verkaufsbericht</TITLE>
				<STYLE TYPE="text/css">
					BODY {font-family:Tahoma,Arial,sans-serif;
					font-size:10pt; font-weight:normal;
					line-height:140%}
					.heading {font-family:Tahoma,Arial,sans-serif;
					font-size:16pt; font-weight:bold}
					.mehr {font-family:Tahoma,Arial,sans-serif;
					font-size:11pt; font-weight:bold}
					.weniger {font-family:Tahoma,Arial,sans-serif;
					font-size:11pt; color:red;
					font-weight:bold}
				</STYLE>
			</HEAD>
			<BODY>
				<DIV CLASS="heading">
					<IMG SRC="Buecher.gif" ALIGN="BOTTOM" HSPACE="10" />
					Buecher - Verkaufsbericht
				</DIV>
				<HR></HR>
				<xsl:for-each select="//Buch">
					<xsl:sort select="Bestellnummer" order="descending" />
					<xsl:apply-templates select="Bestellnummer" />
					<xsl:apply-templates select="Titel" />
					<xsl:apply-templates select="Verkaufszahlen" />
					<xsl:apply-templates select="Erschienen" />
					<HR></HR>
				</xsl:for-each>
			</BODY>
		</HTML>
	</xsl:template>

	<xsl:template match="*">
		<xsl:value-of select="local-name()" />
		:
		<B>
			<xsl:value-of select="." />
		</B>
		<BR />
	</xsl:template>

	<xsl:template match="Verkaufszahlen">
		Verkaeufe im letzten Jahr waren
		<B>
			<xsl:value-of select="format-number(., '#,###,##0')" />
		</B>
		Einheiten.
		<BR />
		<xsl:choose>
			<xsl:when test=". &gt; 35000">
				<SPAN CLASS="mehr">Dies ist mehr als geplant, gutes Ergebnis!</SPAN>
				<BR />
			</xsl:when>
			<xsl:when test=". &lt; 15000">
				<SPAN CLASS="weniger">
					<IMG SRC="Achtung.gif" ALIGN="BOTTOM" HSPACE="5" />
					WARNUNG: dies ist weniger als geplant.
				</SPAN>
				<BR />
			</xsl:when>
			<xsl:otherwise>
				Dies ist im geplanten Rahmen.
				<BR />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="Erschienen">
		<xsl:if test="substring-before(., '-') &lt; 2002">
			Dies ist ein alter Titel und nicht mehr im aktuellen Programm.
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>