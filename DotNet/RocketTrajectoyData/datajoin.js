var fs = require('fs');


var altTime = JSON.parse(fs.readFileSync('JCSAT-Altitude_Time.json'));
var velTime = JSON.parse(fs.readFileSync('JCSAT-Velocity_Time.json'));
var drData = JSON.parse(fs.readFileSync('JCSAT-Altitude_Downrange.json'));


console.log('Starting Data Join');

var trajectories = {stages : []};
// var mapped = JoinAltitudeVelocityDownrange(altTime,velTime,drData);
GenerateAllStageData();
var toWrite = JSON.stringify(trajectories,null,2);

fs.writeFile("results.json", toWrite, function(err) {
    if(err) {
        return console.log(err);
    }
    console.log("The file was saved!");
}); 


function GenerateAllStageData(){

	altTime.data.forEach(function(currValue,index,array){
		trajectories.stages.push({name: currValue.name, data: JoinAltitudeVelocityDownrange(currValue,velTime.data[index],drData.data[index])});
	});
};


function JoinAltitudeVelocityDownrange(altitudeData, velocityData,downRangeData){

	var altFirstStage = altitudeData.y;
	var velFirstStage = velocityData;
	var drFirstStage = downRangeData.x;
	var newData = { profiledata:[]};


	altFirstStage.forEach(function(currValue, index, array){
		var toPush = {
			altitude: currValue,
			velocity: velFirstStage.y[index],
			time: velFirstStage.x[index],
			downrange: drFirstStage[index]
		}
		newData.profiledata.push(toPush);
	});

	newData.profiledata.reduce(function(previousValue,currentValue,currentIndex,array){
		var deltaVelocity =  currentValue.velocity - previousValue.velocity;
		currentValue.deltav = deltaVelocity;
		currentValue.dalt = currentValue.altitude - previousValue.altitude;
		currentValue.ddrange = currentValue.downrange - previousValue.downrange;
		return currentValue;
	});

	return newData;

}





