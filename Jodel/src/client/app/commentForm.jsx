import React from 'react';
import { Button } from 'react-bootstrap';
import { Form, FormGroup, FormControl, Col, HelpBlock } from 'react-bootstrap';

class CommentForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            author: '',
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

    handleAuthorChange(e) {
        this.setState({author: e.target.value});
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
        var author = this.state.author.trim();
        var text = this.state.text.trim();
        var longitude = this.myLongitude;
        var latitude = this.myLatitude;
        // alert("Longitude: " + longitude + "  Latitude: "+ latitude);
        if (!text || !author) {
            return;
        }
        this.props.onCommentSubmit({author: author, text: text, longitude: longitude, latitude: latitude});
        this.setState({author: '', text: '', longitude: 0, latitude: 0});
    }

    render() {
        return (
            <Form horizontal onSubmit = {
                this.handleSubmit.bind(this)
            }>
                <FormGroup controlId="formAuthor">
                    <Col><FormControl
                        type="text"
                        placeholder="Ihr Name"
                        value = {
                            this.state.author
                        }
                        onChange = {
                            this.handleAuthorChange.bind(this)
                        }
                    />
                    </Col>
                    <FormControl.Feedback />
                    <HelpBlock>Bitte Namen eingeben</HelpBlock>
                </FormGroup>

                <FormGroup controlId="formTextarea">
                    <Col>
                    <FormControl componentClass="textarea"
                        placeholder="Sage etwas"
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
                      <Button type = "submit" bsStyle="primary">Abschicken</Button>
                </Col>
               </FormGroup>
            </Form>
        );
	}

    render2() {
        return ( < form className = "commentForm" onSubmit = {
            this.handleSubmit.bind(this)
        } > < input type = "text" placeholder = "Your name" value = {
            this.state.author
        }
        onChange = {
            this.handleAuthorChange.bind(this)
        } /> < input type = "text" placeholder = "Say something..." value = {
            this.state.text
        }
        onChange = {
            this.handleTextChange.bind(this)
        } /> < input type = "hidden" value = {
            this.state.latitude
        } /> < input type = "hidden" value = {
            this.state.longitude
        }
        onChange = {
            this.handleLongitudeChange.bind(this)
        } /> < input type = "submit" value = "Post" / > < /form>
        );
    }
}

export default CommentForm;
