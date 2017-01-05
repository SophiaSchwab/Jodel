import React from 'react';
import PostList from './postList.jsx';
import PostForm from './postForm.jsx';

class PostBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        };
    }

    loadPostsFromServer() {
        fetch(this.props.url, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
        .then(this.status)
        .then(this.json)
        .then(this.processData.bind(this))
        .catch(function(error) {
            console.log('Request failed', error);
        });

    }

    handlePostSubmit(post) {
        var posts = this.state.data;
        // Optimistically set an id on the new comment. It will be replaced by an
        // id generated by the server. In a production application you would likely
        // not use Date.now() for this and would have a more robust system in place.
        post.id = Date.now() - new Date(2016, 8, 1, 1,0,0,0);
        var newPosts = posts.concat([post]);
        this.setState({data: newPosts});

        var formdata = JSON.stringify(post);
        fetch(this.props.url, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            method: 'post',
            body: formdata
        })
        .then(this.status)
        .then(this.json)
        .then(this.processData.bind(this))
        .catch(function(error) {
            console.log('Request failed', error);
        });
    }

    status(response) {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response)
        } else {
            return Promise.reject(new Error(response.statusText))
        }
    }

    json(response) {
        return response.json();
    }

    processData(data) {
        this.setState({data: data});
    }

    componentDidMount() {
        this.loadPostsFromServer();
        setInterval(this.loadPostsFromServer(), this.props.pollInterval);
    }

    render() {
        return (
            <div className="postBox">
                <h1>Jodel</h1>
                <PostList data={this.state.data}/>
                <PostForm onPostSubmit={this.handlePostSubmit.bind(this)}/>
            </div>
        );
    }
};

export default PostBox;