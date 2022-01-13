const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const follow = require('./follow');

class App extends React.Component {

    var root = '/api';

	constructor(props) {
		super(props);
		this.state = {users: [], attributes: [], pageSize: 2, links: {}};
        this.updatePageSize = this.updatePageSize.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onNavigate = this.onNavigate.bind(this);
	}

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
	}

	render() {
		return (
			<UserList users={this.state.users}/>
		)
	}

	loadFromServer(pageSize) {
    	follow(client, root, [
    		{rel: 'users', params: {size: pageSize}}]
    	).then(userCollection => {
    		return client({
    			method: 'GET',
    			path: userCollection.entity._links.profile.href,
    			headers: {'Accept': 'application/schema+json'}
    		}).then(schema => {
    			this.schema = schema.entity;
			    this.links = userCollection.entity._links;
    			return userCollection;
    		});
    	}).then(userCollection => {
          		return userCollection.entity._embedded.users.map(user =>
          				client({
          					method: 'GET',
          					path: user._links.self.href
          				})
          		);
          	}).then(userPromises => {
          		return when.all(userPromises);
          	}).done(userCollection => {
    		this.setState({
    			users: userCollection.entity._embedded.users,
    			attributes: Object.keys(this.schema.properties),
    			pageSize: pageSize,
    			links: userCollection.entity._links});
    	});
    }

    onCreate(newUser) {
    	follow(client, root, ['users']).then(userCollection => {
    		return client({
    			method: 'POST',
    			path: userCollection.entity._links.self.href,
    			entity: newUser,
    			headers: {'Content-Type': 'application/json'}
    		})
    	}).then(response => {
    		return follow(client, root, [
    			{rel: 'users', params: {'size': this.state.pageSize}}]);
    	}).done(response => {
    		if (typeof response.entity._links.last !== "undefined") {
    			this.onNavigate(response.entity._links.last.href);
    		} else {
    			this.onNavigate(response.entity._links.self.href);
    		}
    	});
    }

    onDelete(user) {
    	client({method: 'DELETE', path: user._links.self.href}).done(response => {
    		this.loadFromServer(this.state.pageSize);
    	});
    }

    onUpdate(user, updatedUser) {
    	client({
    		method: 'PUT',
    		path: user.entity._links.self.href,
    		entity: updatedUser,
    		headers: {
    			'Content-Type': 'application/json',
    			'If-Match': user.headers.Etag
    		}
    	}).done(response => {
    		this.loadFromServer(this.state.pageSize);
    	}, response => {
    		if (response.status.code === 412) {
    			alert('DENIED: Unable to update ' +
    				user.entity._links.self.href + '. Your copy is stale.');
    		}
    	});
    }

    onNavigate(navUri) {
    	client({method: 'GET', path: navUri}).done(userCollection => {
    		this.setState({
    			users: userCollection.entity._embedded.users,
    			attributes: this.state.attributes,
    			pageSize: this.state.pageSize,
    			links: userCollection.entity._links
    		});
    	});
    }



    when.promise(async_func_call())
    	.then(function(results) {
    		/* process the outcome of async_func_call */
    	})
    	.then(function(more_results) {
    		/* process the previous then() return value */
    	})
    	.done(function(yet_more) {
    		/* process the previous then() and wrap things up */
    	});
}

class CreateDialog extends React.Component {

    	constructor(props) {
    		super(props);
    		this.handleSubmit = this.handleSubmit.bind(this);
    	}

    	handleSubmit(e) {
    		e.preventDefault();
    		const newUser = {};
    		this.props.attributes.forEach(attribute => {
    			newUser[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
    		});
    		this.props.onCreate(newUser);

    		// clear out the dialog's inputs
    		this.props.attributes.forEach(attribute => {
    			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
    		});

    		// Navigate away from the dialog to hide it.
    		window.location = "#";
    	}

    	render() {
    		const inputs = this.props.attributes.map(attribute =>
    			<p key={attribute}>
    				<input type="text" placeholder={attribute} ref={attribute} className="field"/>
    			</p>
    		);

    		return (
    			<div>
    				<a href="#createUser">Create</a>

    				<div id="createUser" className="modalDialog">
    					<div>
    						<a href="#" title="Close" className="close">X</a>

    						<h2>Create new user</h2>

    						<form>
    							{inputs}
    							<button onClick={this.handleSubmit}>Create</button>
    						</form>
    					</div>
    				</div>
    			</div>
    		)
    	}

    }

class UpdateDialog extends React.Component {

    	constructor(props) {
    		super(props);
    		this.handleSubmit = this.handleSubmit.bind(this);
    	}

    	handleSubmit(e) {
    		e.preventDefault();
    		const updatedUser = {};
    		this.props.attributes.forEach(attribute => {
    			updatedUser[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
    		});
    		this.props.onUpdate(this.props.user, updatedEmployee);
    		window.location = "#";
    	}

    	render() {
    		const inputs = this.props.attributes.map(attribute =>
    			<p key={this.props.user.entity[attribute]}>
    				<input type="text" placeholder={attribute}
    					   defaultValue={this.props.user.entity[attribute]}
    					   ref={attribute} className="field"/>
    			</p>
    		);

    		const dialogId = "updateUser-" + this.props.user.entity._links.self.href;

    		return (
    			<div key={this.props.user.entity._links.self.href}>
    				<a href={"#" + dialogId}>Update</a>
    				<div id={dialogId} className="modalDialog">
    					<div>
    						<a href="#" title="Close" className="close">X</a>

    						<h2>Update a user</h2>

    						<form>
    							{inputs}
    							<button onClick={this.handleSubmit}>Update</button>
    						</form>
    					</div>
    				</div>
    			</div>
    		)
    	}

    }

class UserList extends React.Component{

    constructor(props) {
    		super(props);
    		this.handleNavFirst = this.handleNavFirst.bind(this);
    		this.handleNavPrev = this.handleNavPrev.bind(this);
    		this.handleNavNext = this.handleNavNext.bind(this);
    		this.handleNavLast = this.handleNavLast.bind(this);
    		this.handleInput = this.handleInput.bind(this);
    	}

    handleNavFirst(e){
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.last.href);
    }

	render() {
		const users = this.props.users.map(user =>
			<User key={user._links.self.href} user={user}/>
		);
		return (
		<div>
        	<input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
			<table>
				<tbody>
					<tr>
						<th>{this.props.user.name}</th>
						<th>{this.props.user.email}</th>
					</tr>
					{users}
				</tbody>
			</table>
			<div>
            		{navLinks}
           	</div>
        </div>
		)
	}
}

class User extends React.Component {

	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onDelete(this.props.user);
	}

	render() {
		return (
			<tr>
				<td>{this.props.user.name}</td>
				<td>{this.props.user.email}</td>
				<td>
                	<UpdateDialog user={this.props.user}
                								  attributes={this.props.attributes}
                								  onUpdate={this.props.onUpdate} />
                </td>
				<td>
					<button onClick={this.handleDelete}>Delete</button>
				</td>
			</tr>
		)
	}
}
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
