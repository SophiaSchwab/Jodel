import React from 'react';
import {render} from 'react-dom';
import { Grid } from 'react-bootstrap';
import { Row } from 'react-bootstrap';
import { Col } from 'react-bootstrap';
import PostBox from './postBox.jsx';

class App extends React.Component {
  constructor(){
    super();
    this.state = {
      latitude : 0.0,
      longitude : 0.0,
      city : 'unbekannt'
    };
    this.getLocation();
    }
    getLocation(){
      if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(this.showPosition.bind(this));
      }
      else{
        this.myLatitude = -9999;
        this.myLongitude = -9999;
      }
    }
    showPosition(position){
      this.setState({longitude: position.coords.longitude, latitude: position.coords.latitude});
    }
  render () {
    return (
      <div>
      <Grid>
        <Row className="show-grid">
            <Col xs={12} md={3}>
Longitude: {this.state.longitude}<br />
Latitude: {this.state.latitude}
            </Col>
            <Col xs={6} md={9}>  <PostBox url="/Jodel/rest/posts" pollInterval={2000} name='pb'/>
            </Col>
            </Row>
        </Grid>
      </div>
    );
  }
}

render(<App/>, document.getElementById('app'));
