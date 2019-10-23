<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">
		<HTML>
			<BODY>
				<DIV
					STYLE="font-family:Tahoma,Arial,sans-serif; 
                  font-size:24pt; color:green;
                  text-align:center; letter-spacing:8px;
                  font-weight:bold">
					Eine Liste von Buechern
				</DIV>
				<HR />
				<TABLE WIDTH="100%" CELLPADDING="5">
					<xsl:apply-templates select="//Buch" />
				</TABLE>
				<DIV
					STYLE="font-family:Arial,sans-serif; 
                  font-size:8pt; margin-left:10px">
					E. Ammann, Hochschule Reutlingen, 2010
				</DIV>
			</BODY>
		</HTML>
	</xsl:template>

	<xsl:template match="Buch">
		<TR>
			<xsl:apply-templates select="Gebiet" />
			<xsl:apply-templates select="Titel" />
			<xsl:apply-templates select="Bestellnummer" />
		</TR>
		<TR>
			<xsl:apply-templates select="Erschienen" />
		</TR>
		<TR>
			<TD COLSPAN="3">
				<HR />
			</TD>
		</TR>
	</xsl:template>

	<xsl:template match="Bestellnummer">
		<TD STYLE="font-family:Tahoma,Arial,sans-serif; 
             font-size:10pt">
			Code:
			<B>
				<xsl:value-of select="." />
			</B>
		</TD>
	</xsl:template>

	<xsl:template match="Gebiet">
		<TD ROWSPAN="2"
			STYLE="font-family:Comic Sans MS, Arial,sans-serif; 
             color:darkblue; font-size:16pt; 
             font-weight:bold">
			<xsl:value-of select="." />
			:
		</TD>
	</xsl:template>

	<xsl:template match="Erschienen">
		<TD STYLE="font-family:Tahoma,Arial,sans-serif; 
             font-size:10pt">
			Erschienen:
			<B>
				<xsl:value-of select="." />
			</B>
		</TD>
	</xsl:template>

	<xsl:template match="Titel">
		<TD ROWSPAN="2"
			STYLE="font-family:Lucida Handwriting Italic,Arial,sans-serif; 
             font-size:18pt; font-weight:bold; color:darkred">
			"
			<xsl:value-of select="." />
			"
		</TD>
	</xsl:template>
</xsl:stylesheet>
