import React from 'react';
import ShowPicture from './src/ShowPicture';
import TakePicture from './src/TakePicture'
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
const Stack = createNativeStackNavigator();
const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={TakePicture}
        />
        <Stack.Screen
          name="Image"
          component={ShowPicture}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
export default App;
