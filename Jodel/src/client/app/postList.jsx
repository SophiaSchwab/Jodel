import React from 'react';
import Post from './post.jsx';
import { Button } from 'react-bootstrap';
import { Form, FormGroup, FormControl, Col, HelpBlock } from 'react-bootstrap';
class PostList extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var postNodes = this.props.data.map(function(post) {
            return (
                <Post author={post.author} key={post.id}>
                    {post.text}
                    <FormGroup>
                    <Col>
                            <button type="button" class="btn btn-primary btn-block">Upvote</button>
                    </Col>
                   </FormGroup>
                </Post>
                );
        });
        return (
            <div className="postList">
                {postNodes}
            </div>
        );
    }
}

export default PostList;
