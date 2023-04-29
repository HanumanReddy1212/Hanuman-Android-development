from flask import Flask
from flask import request
from werkzeug.utils import secure_filename
import os.path
from flask import jsonify

app = Flask(__name__)


@app.route("/", methods=['GET','POST'])
def upload():
    file = request.files['image']
    filename = secure_filename(file.filename)

    file_upload = 'user_demo'
    if not os.path.exists(file_upload):
        os.makedirs(file_upload)
    updatedFileName = os.path.join(file_upload, filename)
    file.save(updatedFileName)
    print("\n Received: " + updatedFileName)
    resp = jsonify(success=True)
    return resp


if __name__ == "__main__":
    app.run(host="0.0.0.0")
