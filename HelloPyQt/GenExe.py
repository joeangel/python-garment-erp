from distutils.core import setup
import py2exe

#setup(windows=["My.py",
#{"script":"My.py",
#"icon_resources":[(1, "My.ico")]
#}
#],
#data_files=[
#("bmp",
#["bmp/logo.bmp", "bmp/title.gif"]
#)
#]
#)

#setup(windows=["Hellomain.py",
#{"script":"Hellomain.py"
#}
#],
#data_files=[
#]
#)

#setup(
#    options = {
#      "py2exe": {
#        "dll_excludes": ["MSVCP90.dll"],
#      }
#    },
#    windows = [
#      "Hellomain.py", {
#        "script": "Hellomain.py",
#      }
#    ]
#)

setup(
          name         = "DlgHello",
          description  = "Hello",
          author       = "Angel",
          author_email = "email@dn",
          windows = [{"script":"DlgHello.py",}],
          version = "1.0",
          options = { "py2exe": {"compressed"  : 1,
                                 "optimize"    : 2,
                                 "ascii"       : 0,
                                 "bundle_files": 3,
                                 "includes":["sip"],
                                 "dll_excludes": ["MSVCP90.dll"]},},
          #zipfile = None
        )
