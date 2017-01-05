import React from 'react';
import { Button } from 'react-bootstrap';
import { Form, FormGroup, FormControl, Col, HelpBlock } from 'react-bootstrap';

class PostForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tuser: '',
            text: '',
            latitude: -5.0,
            longitude: -5.0
        };

        this.myLongitude = 9.0;
        this.myLatitude = 9.0;
        this.getLocation();
    }

    getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(this.showPosition.bind(this));
        } else {
            this.myLatitude = -9999;
            this.myLongitude = -9999;
        }
    }

    showPosition(position) {
        this.myLatitude = position.coords.latitude;
        this.myLongitude = position.coords.longitude;
    }

    handletuserChange(e) {
        this.setState({tuser: e.target.value});
    }

    handleTextChange(e) {
        this.setState({text: e.target.value});
    }

    handleLongitudeChange(e) {
        this.setState({longitude: e.target.value});
    }

    handleSubmit(e) {
        e.preventDefault();
        // this.getLocation().bind(this);
        var tuser = this.state.tuser.trim();
        var text = this.state.text.trim();
        var longitude = this.myLongitude;
        var latitude = this.myLatitude;
        // alert("Longitude: " + longitude + "  Latitude: "+ latitude);
        if (!text || !tuser) {
            return;
        }
        this.props.onPostSubmit({tuser: {id: 2, email: 'xxx', name:'Myname'}, text: text, longitude: longitude,
                                 latitude: latitude});
        this.setState({tuser: '', text: '', longitude: 0, latitude: 0});
    }

    render() {
        return (
            <Form horizontal onSubmit = {
                this.handleSubmit.bind(this)
            }>
                <FormGroup controlId="formtuser">
                    <Col><FormControl
                        type="text"
                        placeholder="Your name"
                        value = {
                            this.state.tuser
                        }
                        onChange = {
                            this.handletuserChange.bind(this)
                        }
                    />
                    </Col>
                    <FormControl.Feedback />
                    <HelpBlock>Please enter your name</HelpBlock>
                </FormGroup>

                <FormGroup controlId="formTextarea">
                    <Col>
                    <FormControl componentClass="textarea"
                        placeholder="Say something"
                        value = {
                               this.state.text
                           }
                        onChange = {
                               this.handleTextChange.bind(this)
                        }/>
                        </Col>
                </FormGroup>
                < input type = "hidden" value = {
                           this.state.latitude
                       } /> < input type = "hidden" value = {
                           this.state.longitude
                       }
                       onChange = {
                           this.handleLongitudeChange.bind(this)
                       } />
                <FormGroup>
                <Col>
                      <Button type = "submit" bsStyle="primary">Posten</Button>
                        <button type="button" class="btn btn-primary btn-block">Button 1</button>
                </Col>
               </FormGroup>
            </Form>
        );
	}
}

export default PostForm;
