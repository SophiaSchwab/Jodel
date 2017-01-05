import React from 'react';
import Comment from './comment.jsx';
class CommentList extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var commentNodes = this.props.data.map(function(comment) {
            return (
                <Comment author={comment.author} key={comment.id}>
                    {comment.text}
                </Comment>
            );
        });
        return (
            <div className="commentList">
                {commentNodes}
            </div>
        );
    }
}

export default CommentList;
