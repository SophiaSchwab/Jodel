import React from 'react';
import Remarkable from 'remarkable';

class Comment extends React.Component {

    constructor(props) {
        super(props);
    }

    rawMarkup() {
        var md = new Remarkable();
        var rawMarkup = md.render(this.props.children.toString());
        return {__html: rawMarkup};
    }

    render() {
        var result = (
            <div className="comment">
                <h2 className="commentAuthor">
                    {this.props.author}
                </h2>
                <span dangerouslySetInnerHTML={this.rawMarkup()}/>
            </div>
        );
//        console.log(result);
        return result;
    }
}

export default Comment;
