# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'D:\Python_tools\Example\Eric4\HelloPyQt\DlgHello.ui'
#
# Created: Sun Feb 13 17:45:24 2011
#      by: PyQt4 UI code generator 4.8.3
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DlgHello(object):
    def setupUi(self, DlgHello):
        DlgHello.setObjectName(_fromUtf8("DlgHello"))
        DlgHello.resize(400, 300)
        self.lblHello = QtGui.QLabel(DlgHello)
        self.lblHello.setGeometry(QtCore.QRect(150, 100, 71, 16))
        self.lblHello.setObjectName(_fromUtf8("lblHello"))
        self.btnHello = QtGui.QPushButton(DlgHello)
        self.btnHello.setGeometry(QtCore.QRect(80, 180, 75, 23))
        self.btnHello.setObjectName(_fromUtf8("btnHello"))
        self.btnExit = QtGui.QPushButton(DlgHello)
        self.btnExit.setGeometry(QtCore.QRect(200, 180, 75, 23))
        self.btnExit.setObjectName(_fromUtf8("btnExit"))

        self.retranslateUi(DlgHello)
        QtCore.QObject.connect(self.btnExit, QtCore.SIGNAL(_fromUtf8("clicked()")), DlgHello.close)
        QtCore.QMetaObject.connectSlotsByName(DlgHello)

    def retranslateUi(self, DlgHello):
        DlgHello.setWindowTitle(QtGui.QApplication.translate("DlgHello", "Hello, PyQt", None, QtGui.QApplication.UnicodeUTF8))
        self.lblHello.setText(QtGui.QApplication.translate("DlgHello", "Hello, PyQt", None, QtGui.QApplication.UnicodeUTF8))
        self.btnHello.setText(QtGui.QApplication.translate("DlgHello", u"你好", None, QtGui.QApplication.UnicodeUTF8))
        self.btnExit.setText(QtGui.QApplication.translate("DlgHello", u"退出", None, QtGui.QApplication.UnicodeUTF8))


if __name__ == "__main__":
    import sys
    app = QtGui.QApplication(sys.argv)
    DlgHello = QtGui.QDialog()
    ui = Ui_DlgHello()
    ui.setupUi(DlgHello)
    DlgHello.show()
    sys.exit(app.exec_())

