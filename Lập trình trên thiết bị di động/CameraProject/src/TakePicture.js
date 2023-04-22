import React, { useState, useEffect } from 'react';
import { StyleSheet, Text, View, Button, Image } from 'react-native';
import { Camera } from 'expo-camera';
const TakePicture = ({navigation}) => {
  const [hasCameraPermission, setHasCameraPermission] = useState(null);
  const [camera, setCamera] = useState(null);
  const [type, setType] = useState(Camera.Constants.Type.front);
  useEffect(() => {
    (async () => {
      const cameraStatus = await Camera.requestCameraPermissionsAsync();
      setHasCameraPermission(cameraStatus.status === 'granted');
  })();
  }, []);
  const takePicture = async () => {
    if(camera){
        const data = await camera.takePictureAsync(null);
        navigation.navigate('Image', {picture_source: data.uri});
    }
  }

  if (hasCameraPermission === false) {
    return <Text>No access to camera</Text>;
  }
  return (
   <View style={{flex: 1, marginBottom:20}}>
      <View style={{flex: 1, marginBottom: 10}}>
            <Camera
            ref={ref => setCamera(ref)}
            style={{flex: 1}}
            type={type}/>
      </View>

       <Button title="Take Picture" onPress={() => takePicture()}/>
   </View>
  );
}
export default TakePicture;