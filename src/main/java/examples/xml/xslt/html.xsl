<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="daten">
   <html>
     <body>
       <h2>Das Umsatzergebnis des letzten Jahres in Millionen Euro</h2>
       <table border="3">
         <tr>
           <td><b>Quartal</b></td>
           <td><b>Umsatz</b></td>
         </tr>
         <xsl:for-each select="ergebnis">
           <tr>
             <td>
               <xsl:value-of select="@id"/>
             </td>
             <td>
               <xsl:value-of select="wert"/>
             </td>
           </tr>
         </xsl:for-each>
      </table>
    </body>
  </html>
</xsl:template>
</xsl:stylesheet>