<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Bibliography</title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="bib">
        <h1>Domaines</h1>
        <br/>
        <xsl:for-each select="/bib/domain/title">
            <h2 style="margin:0px;">
                <a href="{concat('#', .)}">
                    <xsl:value-of select="."/>
                </a>
            </h2>
            <br/>
        </xsl:for-each>
        <hr/>
        <hr/>
        <xsl:apply-templates select="domain"/>
    </xsl:template>

    <xsl:template match="/bib/domain/title">
        <h1>Domaines</h1>
        <br/>
        <h2 style="margin:0px;">
            <a href="{concat('#', .)}">
                <xsl:value-of select="."/>
            </a>
        </h2>
        <br/>
        <hr/>
        <hr/>
        <xsl:apply-templates select="domain"/>
    </xsl:template>

    <xsl:template match="domain">
        <div style="width:100%;background-color:#C0C0C0;border:1px solid black;">
            <h2 id="{title}">
                <xsl:value-of select="title"/>
            </h2>
            <br/>
        </div>
        <hr/>
        <xsl:apply-templates select="bib_ref"/>

    </xsl:template>

    <xsl:template match="bib_ref">
        <h3>
            Titre:
            <xsl:value-of select="title"/>
        </h3>
        Auteur(s):<xsl:value-of select="author"/>
        <br/>
        Annee:
        <xsl:value-of select="year"/>
        <br/>
        Lien:
        <a href="{weblink}">
            <xsl:value-of select="weblink"/>
        </a>
        <br/>
        Commentaires:
        <xsl:value-of select="comment"/>
        <br/>
        <br/>
        <hr/>
    </xsl:template>

</xsl:stylesheet>