import Link from 'next/link'
import React from 'react'

const NavBar = () => {
  return (
    <header>
      <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
        <div className="container-fluid">
          <Link className="navbar-brand" href="/">Logo</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="collapsibleNavbar">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <Link className="nav-link" href="/bookmarks/add">Add Bookmark</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
  )
}

export default NavBar
