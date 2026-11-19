require tree-sitter-grammar.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f7314d574b0c64fe08b05298f291b604"

SRC_URI = "git://github.com/tree-sitter-grammars/tree-sitter-markdown.git;protocol=https;branch=split_parser;tag=v${PV}"
SRCREV = "41ff24a6c775492c543b09228aeb98d1cd2b3a0f"

TS_PARSERS = "markdown:tree-sitter-markdown markdown_inline:tree-sitter-markdown-inline"
