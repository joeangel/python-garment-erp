# -*- coding: utf-8 -*-

"""
Module implementing DlgHello.
"""
import sys,  PyQt4
from PyQt4.QtGui import QDialog
from PyQt4.QtCore import pyqtSignature

from Ui_DlgHello import Ui_DlgHello

class DlgHello(QDialog, Ui_DlgHello):
    """
    Class documentation goes here.
    """
    def __init__(self, parent = None):
        """
        Constructor
        """
        QDialog.__init__(self, parent)
        self.setupUi(self)
    
    @pyqtSignature("")
    def on_btnHello_clicked(self):
        """
        Slot documentation goes here.
        """
        self.lblHello.setText(u"你好，PyQt4")
        # TODO: not implemented yet
        #raise NotImplementedError

if __name__ == "__main__":
    app = PyQt4.QtGui.QApplication(sys.argv)
    dlg = DlgHello()
    dlg.show()
    sys.exit(app.exec_())
