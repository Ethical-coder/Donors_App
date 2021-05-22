from flask import Flask, render_template, flash, redirect, request, url_for, session, logging
from flask_mysqldb import MySQL
from flask import jsonify
import numpy as np
import haversine as hs



app = Flask(__name__)
app.secret_key='some secret key'


#Config MySQL
app.config['MYSQL_HOST']='localhost'
app.config['MYSQL_USER']='root'
app.config['MYSQL_PASSWORD']='Pratya123#'
app.config['MYSQL_DB']='covid'
app.config['MYSQL_CURSORCLASS']='DictCursor'
#init MySQL
mysql =  MySQL(app)


@app.route('/donate', methods=['POST'])
def donate():
    print(request.form["idDonor"])
    if request.method == 'POST':
        idDonor = request.get_json["idDonor"]
        address = request.get_json["address"]
        bloodGroup = request.get_json["bloodGroup"]
        latitude=request.get_json['latitude']
        longitude=request.get_json['latitude']
        disease = request.get_json['disease']
    
        
        #create a cursor
        cur = mysql.connection.cursor()

        #Inserting values into tables
        cur.execute("INSERT INTO donor('idDonor','Address','BloodGroup','Latitude','Longitude','Disease') VALUES(%s, %s, %s,%s, %s,%s)" ,(idDonor, address, bloodGroup,latitude,longitude,disease))
       
        #Commit to DB
        mysql.connection.commit()
        #close connection
        cur.close()
        flash('Your request is successfully sent to the Blood Bank','success')
        

    return jsonify({ "status": "ok"})


@app.route('/plasma/request', methods = ['GET'])
def plasma_request():
    if(request.method == 'GET'):
        blood_group = request.args.get('group', type=str)
        print(blood_group)
        latitude = request.args.get('lat', type=float)
        longitude = request.args.get('long', type=float)
        query = "Select * from donor where BloodGroup=\'{group}\';".format(group=blood_group)
        print(query)
        cur = mysql.connection.cursor()
        cur.execute(query)
        result = cur.fetchall()
        cur.close()
        
        if len(result)>0:
            new_result = []
            for record in result:
                print(record)
                record['distance'] = haversine_distance(latitude, longitude, record['Latitude'], record['Longitude'])
                print(record)
                new_result.append(record)
                
            new_result.sort(key=compare)
            
            return jsonify({'data': new_result})
                
        
        return jsonify({'data': ""})

def compare(rec):
    return rec['distance']

def haversine_distance(lat1, lon1, lat2, lon2):
    loc1=(lat1,lon1)
    loc2=(lat2,lon2)
    
    return hs.haversine(loc1,loc2)
#    print(lat1,lon1,lat2,lon2)
#    r = 6371
#    phi1 = np.radians(lat1)
#    phi2 = np.radians(lat2)
#    delta_phi = np.radians(lat2 - lat1)
#    delta_lambda = np.radians(lon2 - lon1)
#    a = np.sin(delta_phi / 2)*2 + np.cos(phi1) * np.cos(phi2) *   np.sin(delta_lambda / 2)*2
#    res = r * (2 * np.arctan2(np.sqrt(a), np.sqrt(1 - a)))
#    return np.round(res, 2)


if __name__ == '__main__':
    app.run(debug=True)
