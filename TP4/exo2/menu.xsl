<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Menu</title>
            </head>
            <body>
                <h1>Menu</h1>
                <xsl:apply-templates select="breakfast_menu/food"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="food">
        <div>
            <div style="background-color: teal;color: white; font-size: 30px">
                <xsl:value-of select="name"/> -
                <xsl:value-of select="price"/>
            </div>
            <div style="background-color: lightgrey; padding-left: 20px;padding-bottom: 10px;">
                <xsl:value-of select="description"/>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>
