SUMMARY = "Customizable user account management for Flask"
DESCRIPTION = "Customizable User Account Management for Flask; Register \
Confirm email, Login, Change username, Change password, Forgot Password \
and more."
HOMEPAGE = "https://github.com/lingthio/Flask-User"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5aabce758ead32427c145173bc555403"

SRC_URI[sha256sum] = "c9785febc2fc7ca15c69cfff1c8dabc2788f25043b54a33f73a6c2a9daf7fbe4"

PYPI_PACKAGE_SDIST = "Flask-User"

CVE_PRODUCT = "flask-user_project:flask-user"

inherit pypi setuptools3

# setup_requires would make setuptools fetch Flask-Login from PyPI at build time
do_configure:prepend() {
    sed -i -e "s/setup_requires=\['Flask-Login',\] + pytest_runner/setup_requires=pytest_runner/" ${S}/setup.py
}

RDEPENDS:${PN} = " \
    python3-babel \
    python3-bcrypt \
    python3-blinker \
    python3-cryptography \
    python3-flask \
    python3-flask-login \
    python3-flask-mail \
    python3-flask-sqlalchemy \
    python3-flask-wtf \
    python3-passlib \
"
